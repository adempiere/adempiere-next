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

package org.eevolution.context.workflow.infrastructure.domain.repository

import io.getquill.NamingStrategy
import io.getquill.context.jdbc.JdbcContext
import io.getquill.context.sql.idiom.SqlIdiom
import org.eevolution.context.workflow.domain.model.WfResponsible
import org.eevolution.context.workflow.domain.wfResponsibleRepositoryApi.WfResponsibleRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfResponsible Repository Base Trait with the implementation for WfResponsible Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfResponsible Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfResponsibleRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfResponsibleRepository.Service {

    import context._
  
		override def getByName(name: String): RIO[WfResponsibleRepository, Option[WfResponsible]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfResponsible].filter(_.name == lift(name))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfResponsibleRepository, List[WfResponsible]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfResponsible].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByWfResponsibleId(wfResponsibleId: Id): RIO[WfResponsibleRepository, Option[WfResponsible]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfResponsible].filter(_.wfResponsibleId == lift(wfResponsibleId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfResponsibleRepository, List[WfResponsible]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfResponsible])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfResponsibleRepository,List[WfResponsible]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfResponsible].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfResponsible: WfResponsible): RIO[Any, WfResponsible] = RIO.fromTry(
       Try(EntityWrapper.save(wfResponsible, wfResponsibleProperties)) 
    )
    
		implicit val wfResponsibleSchemaMeta : context.SchemaMeta[WfResponsible] = 
     schemaMeta[WfResponsible] ("AD_WF_Responsible",
     _.isActive -> "IsActive" ,
     _.responsibleType -> "ResponsibleType" ,
     _.createdBy -> "CreatedBy" ,
     _.name -> "Name" ,
     _.created -> "Created" ,
     _.userId -> "AD_User_ID" ,
     _.roleId -> "AD_Role_ID" ,
     _.description -> "Description" ,
     _.clientId -> "AD_Client_ID" ,
     _.updated -> "Updated" ,
     _.orgId -> "AD_Org_ID" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.updatedBy -> "UpdatedBy" ,
     _.entityType -> "EntityType" ,
     _.uuid -> "UUID" )

     def wfResponsibleProperties: Seq[(String, String)] = wfResponsibleSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
