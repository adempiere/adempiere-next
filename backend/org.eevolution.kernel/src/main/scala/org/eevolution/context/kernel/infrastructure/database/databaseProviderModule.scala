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

package org.eevolution.context.kernel.infrastructure

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.{Literal, OracleDialect, PostgresDialect}
import oracle.jdbc.pool.OracleDataSource
import org.eevolution.context.kernel.infrastructure.databaseConfigProviderApi.DatabaseConfigProvider
import org.eevolution.context.kernel.infrastructure.databaseContextApi.DatabaseContext
import org.eevolution.context.kernel.infrastructure.databaseContextApi.DatabaseContext.DatabaseContext
import org.eevolution.context.kernel.infrastructure.databaseProviderApi.DatabaseProvider
import org.eevolution.context.kernel.infrastructure.database.{
  DatabaseConfig,
  databaseProviderBase
}
import org.eevolution.context.kernel.infrastructure.oracleContextApi.OracleContext
import org.eevolution.context.kernel.infrastructure.postgresContextApi.PostgresContext
import zio.{Has, UIO, ZIO, ZLayer, ZManaged}

import javax.sql.DataSource
import scala.util.Try

package object databaseProviderModule extends databaseProviderBase {

  object DatabaseProviderLayer {

    val live: ZLayer[DatabaseConfigProvider, Throwable, DatabaseProvider] =
      ZLayer.fromManaged {
        for {
          config <- databaseConfigProviderApi.config.toManaged_
          dataSource <- ZIO.fromTry {
            Try {
              config.databaseType match {
                case "Oracle" =>
                  val oracleDataSource = new OracleDataSource
                  oracleDataSource.setDatabaseName(config.database)
                  oracleDataSource.setUser(config.user)
                  oracleDataSource.setPassword(config.password)
                  oracleDataSource.setPortNumber(config.port)
                  oracleDataSource.setServerName(config.host)
                  val poolConnectionConfig = new HikariConfig()
                  poolConnectionConfig.setDataSource(oracleDataSource)
                  val dataSource = new HikariDataSource(poolConnectionConfig)
                  dataSource.asInstanceOf[DataSource]
                case "PostgreSQL" =>
                  val postgresqlDataSource =
                    new org.postgresql.ds.PGSimpleDataSource()
                  postgresqlDataSource.setDatabaseName(config.database)
                  postgresqlDataSource.setUser(config.user)
                  postgresqlDataSource.setPassword(config.password)
                  postgresqlDataSource.setPortNumbers(Array(config.port))
                  postgresqlDataSource.setServerNames(Array(config.host))
                  val poolConnectionConfig = new HikariConfig()
                  poolConnectionConfig.setDataSource(postgresqlDataSource)
                  val dataSource = new HikariDataSource(poolConnectionConfig)
                  dataSource.asInstanceOf[DataSource]
                case _ =>
                  throw new RuntimeException(
                    s"$config.databaseType is not supported"
                  )
              }
            }
          }.toManaged_
          databaseProvider <- config.databaseType match {
            case "PostgreSQL" =>
              ZManaged
                .fromAutoCloseable(
                  UIO(
                    (new PostgresContext.Service(dataSource)
                      with DatabaseContext.Service[PostgresDialect, Literal])
                  )
                )
                .map(ctx =>
                  new DatabaseProvider.Service {
                    override def database: DatabaseContext = Has(ctx)

                    override def dataSource
                        : ZIO[DatabaseConfigProvider, Throwable, DataSource] =
                      ???

                    override def config: ZIO[
                      DatabaseConfigProvider,
                      Throwable,
                      DatabaseConfig
                    ] =
                      ???
                  }
                )
            case "Oracle" =>
              ZManaged
                .fromAutoCloseable(
                  UIO(
                    (new OracleContext.Service(dataSource)
                      with DatabaseContext.Service[OracleDialect, Literal])
                  )
                )
                .map(ctx =>
                  new DatabaseProvider.Service {
                    override def database: DatabaseContext = Has(ctx)

                    override def dataSource
                        : ZIO[DatabaseConfigProvider, Throwable, DataSource] =
                      ???

                    override def config: ZIO[
                      DatabaseConfigProvider,
                      Throwable,
                      DatabaseConfig
                    ] =
                      ???
                  }
                )
          }
        } yield databaseProvider
      }
  }

}
