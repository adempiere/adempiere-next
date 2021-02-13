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

import com.zaxxer.hikari.HikariDataSource
import io.getquill.{Literal, PostgresDialect, PostgresJdbcContext}
import org.eevolution.context.kernel.infrastructure.databaseContextApi.DatabaseContext
import zio.blocking.Blocking
import zio.{Has, RIO}

import javax.sql.DataSource

package object postgresContextApi {

  type PostgresContext = Has[PostgresContext.Service]

  object PostgresContext {

    class Service(
        dataSource: DataSource
    ) extends PostgresJdbcContext(
          Literal,
          dataSource.asInstanceOf[HikariDataSource]
        )
        with DatabaseContext.Service[PostgresDialect, Literal] {

      def performEffect[T](
          io: IO[T, _],
          blocking: Blocking.Service
      ): RIO[Blocking, Result[T]] =
        blocking.blocking(RIO(performIO(io)))

      def performEffect_(
          io: IO[_, _],
          blocking: Blocking.Service
      ): RIO[Blocking, Result[Unit]] =
        performEffect(io, blocking).unit
    }

  }

}
