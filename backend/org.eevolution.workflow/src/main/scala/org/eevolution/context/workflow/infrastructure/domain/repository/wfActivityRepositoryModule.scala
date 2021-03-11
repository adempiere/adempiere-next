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
 */

package org.eevolution.context.workflow.infrastructure.domain

import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import io.getquill.{ Literal, NamingStrategy }
import org.eevolution.context.workflow.domain.wfActivityRepositoryApi.WfActivityRepository
import org.eevolution.context.workflow.infrastructure.domain.repository.wfActivityRepositoryBase
import org.eevolution.context.kernel.infrastructure.databaseProviderApi.DatabaseProvider
import zio.blocking.Blocking
import zio.ZLayer

/*
* WfActivity Repository Module Package Object with the implementation for WfActivity Repository
*
* Default implementation to WfActivity Repository Base
* this class is generated from the Application Dictionary if it does not exist and 
* extends from the base contract class, the developer can add or modify the Api or create their own contract
*/
package object wfActivityRepositoryModule extends wfActivityRepositoryBase {

 case class Live[Dialect <: SqlIdiom, Naming <: NamingStrategy](
   override val context: JdbcContext[Dialect, Naming],
   override val blocking: Blocking.Service
 ) extends Service(context, blocking) {

 }

  object WfActivityRepositoryLayer {
  
    val live: ZLayer[DatabaseProvider with Blocking, Nothing, WfActivityRepository] =
      ZLayer.fromServices[
        DatabaseProvider.Service,
        Blocking.Service,
        WfActivityRepository.Service
      ] { (databaseProvider, blocking) =>
        Live(
          databaseProvider.database.get.asInstanceOf[JdbcContext[SqlIdiom, Literal]],
          blocking
        )
      }
      
  }
 }
