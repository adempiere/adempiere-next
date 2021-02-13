/**
  * Copyright (C) 2003-2021, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  **/

package org.eevolution.context.kernel.infrastructure.database

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.NamingStrategy
import io.getquill.context.sql.idiom.SqlIdiom
import org.eevolution.context.kernel.infrastructure.databaseConfigProviderApi.DatabaseConfigProvider
import org.eevolution.context.kernel.infrastructure.databaseContextApi.DatabaseContext
import zio.ZIO

import javax.sql.DataSource
import scala.util.Try

trait databaseProviderBase {

  abstract class Service(
      databaseConfigProvider: DatabaseConfigProvider.Service
  ) {

    def database: DatabaseContext.Service[SqlIdiom, NamingStrategy]

    def dataSource: ZIO[Any, Throwable, DataSource] =
      for {
        databaseConfig: DatabaseConfig <- databaseConfigProvider.config
        datasource <- ZIO.fromTry {
          Try {
            val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
            pgDataSource.setDatabaseName(databaseConfig.database)
            pgDataSource.setUser(databaseConfig.user)
            pgDataSource.setPassword(databaseConfig.password)
            pgDataSource.setPortNumbers(Array(databaseConfig.port))
            pgDataSource.setServerNames(Array(databaseConfig.host))
            val poolConnectionConfig = new HikariConfig()
            poolConnectionConfig.setDataSource(pgDataSource)
            val dataSource = new HikariDataSource(poolConnectionConfig)
            dataSource
          }
        }
      } yield datasource

    def config: ZIO[Any, Throwable, DatabaseConfig] =
      for {
        databaseConfig <- databaseConfigProvider.config
      } yield databaseConfig
  }

}
