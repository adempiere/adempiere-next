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

import org.eevolution.context.kernel.infrastructure.databaseConfigProviderApi.DatabaseConfigProvider
import org.eevolution.context.kernel.infrastructure.databaseProviderApi.DatabaseProvider
import zio.ZIO

import javax.sql.DataSource

class databaseContextApiAccessors {

  def dataSource: ZIO[
    DatabaseProvider with DatabaseConfigProvider,
    Throwable,
    DataSource
  ] =
    ZIO.accessM(_.get.dataSource)

  def config: ZIO[
    DatabaseConfigProvider with DatabaseProvider,
    Throwable,
    DatabaseConfig
  ] = ZIO.accessM(_.get.config)

}
