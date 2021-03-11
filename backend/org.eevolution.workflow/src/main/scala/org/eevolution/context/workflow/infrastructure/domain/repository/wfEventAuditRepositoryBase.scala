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
import org.eevolution.context.workflow.domain.model.WfEventAudit
import org.eevolution.context.workflow.domain.wfEventAuditRepositoryApi.WfEventAuditRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfEventAudit Repository Base Trait with the implementation for WfEventAudit Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfEventAudit Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfEventAuditRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfEventAuditRepository.Service {

    import context._
  
		override def getByAttributeName(attributeName: Option[String]): RIO[WfEventAuditRepository, List[WfEventAudit]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfEventAudit].filter(_.attributeName == lift(attributeName)))
          )
        )
      )
      
		override def getByWfEventAuditId(wfEventAuditId: Id): RIO[WfEventAuditRepository, Option[WfEventAudit]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfEventAudit].filter(_.wfEventAuditId == lift(wfEventAuditId))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfEventAuditRepository, List[WfEventAudit]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfEventAudit].filter(_.description == lift(description)))
          )
        )
      )
      
     override def getAll: RIO[WfEventAuditRepository, List[WfEventAudit]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfEventAudit])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfEventAuditRepository,List[WfEventAudit]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfEventAudit].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfEventAudit: WfEventAudit): RIO[Any, WfEventAudit] = RIO.fromTry(
       Try(EntityWrapper.save(wfEventAudit, wfEventAuditProperties)) 
    )
    
		implicit val wfEventAuditSchemaMeta : context.SchemaMeta[WfEventAudit] = 
     schemaMeta[WfEventAudit] ("AD_WF_EventAudit",
     _.attributeName -> "AttributeName" ,
     _.userId -> "AD_User_ID" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.isActive -> "IsActive" ,
     _.updatedBy -> "UpdatedBy" ,
     _.newValue -> "NewValue" ,
     _.orgId -> "AD_Org_ID" ,
     _.updated -> "Updated" ,
     _.createdBy -> "CreatedBy" ,
     _.wfState -> "WFState" ,
     _.created -> "Created" ,
     _.oldValue -> "OldValue" ,
     _.wfEventAuditId -> "AD_WF_EventAudit_ID" ,
     _.eventType -> "EventType" ,
     _.description -> "Description" ,
     _.wfProcessId -> "AD_WF_Process_ID" ,
     _.tableId -> "AD_Table_ID" ,
     _.textMsg -> "TextMsg" ,
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.recordId -> "Record_ID" ,
     _.elapsedTimeMS -> "ElapsedTimeMS" ,
     _.uuid -> "UUID" )

     def wfEventAuditProperties: Seq[(String, String)] = wfEventAuditSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
