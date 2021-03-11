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
import org.eevolution.context.workflow.domain.model.WfProcess
import org.eevolution.context.workflow.domain.wfProcessRepositoryApi.WfProcessRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfProcess Repository Base Trait with the implementation for WfProcess Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfProcess Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfProcessRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfProcessRepository.Service {

    import context._
  
		override def getByWorkflowId(workflowId: TableDirect): RIO[WfProcessRepository, List[WfProcess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcess].filter(_.workflowId == lift(workflowId)))
          )
        )
      )
      
		override def getByWfProcessId(wfProcessId: Id): RIO[WfProcessRepository, Option[WfProcess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcess].filter(_.wfProcessId == lift(wfProcessId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfProcessRepository, List[WfProcess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcess])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfProcessRepository,List[WfProcess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcess].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfProcess: WfProcess): RIO[Any, WfProcess] = RIO.fromTry(
       Try(EntityWrapper.save(wfProcess, wfProcessProperties)) 
    )
    
		implicit val wfProcessSchemaMeta : context.SchemaMeta[WfProcess] = 
     schemaMeta[WfProcess] ("AD_WF_Process",
     _.created -> "Created" ,
     _.processing -> "Processing" ,
     _.updated -> "Updated" ,
     _.processed -> "Processed" ,
     _.isActive -> "IsActive" ,
     _.workflowId -> "AD_Workflow_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.createdBy -> "CreatedBy" ,
     _.orgId -> "AD_Org_ID" ,
     _.wfState -> "WFState" ,
     _.userId -> "AD_User_ID" ,
     _.updatedBy -> "UpdatedBy" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.wfProcessId -> "AD_WF_Process_ID" ,
     _.messageId -> "AD_Message_ID" ,
     _.textMsg -> "TextMsg" ,
     _.tableId -> "AD_Table_ID" ,
     _.priority -> "Priority" ,
     _.recordId -> "Record_ID" ,
     _.uuid -> "UUID" )

     def wfProcessProperties: Seq[(String, String)] = wfProcessSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
