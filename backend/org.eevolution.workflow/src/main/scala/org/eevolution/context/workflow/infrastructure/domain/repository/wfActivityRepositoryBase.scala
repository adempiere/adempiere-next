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
import org.eevolution.context.workflow.domain.model.WfActivity
import org.eevolution.context.workflow.domain.wfActivityRepositoryApi.WfActivityRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfActivity Repository Base Trait with the implementation for WfActivity Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfActivity Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfActivityRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfActivityRepository.Service {

    import context._
  
		override def getByWfNodeId(wfNodeId: TableDirect): RIO[WfActivityRepository, List[WfActivity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivity].filter(_.wfNodeId == lift(wfNodeId)))
          )
        )
      )
      
		override def getByWfActivityId(wfActivityId: Id): RIO[WfActivityRepository, Option[WfActivity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivity].filter(_.wfActivityId == lift(wfActivityId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfActivityRepository, List[WfActivity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivity])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfActivityRepository,List[WfActivity]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivity].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfActivity: WfActivity): RIO[Any, WfActivity] = RIO.fromTry(
       Try(EntityWrapper.save(wfActivity, wfActivityProperties)) 
    )
    
		implicit val wfActivitySchemaMeta : context.SchemaMeta[WfActivity] = 
     schemaMeta[WfActivity] ("AD_WF_Activity",
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.createdBy -> "CreatedBy" ,
     _.userId -> "AD_User_ID" ,
     _.wfActivityId -> "AD_WF_Activity_ID" ,
     _.wfProcessId -> "AD_WF_Process_ID" ,
     _.isActive -> "IsActive" ,
     _.processed -> "Processed" ,
     _.processing -> "Processing" ,
     _.wfState -> "WFState" ,
     _.created -> "Created" ,
     _.updatedBy -> "UpdatedBy" ,
     _.clientId -> "AD_Client_ID" ,
     _.messageId -> "AD_Message_ID" ,
     _.updated -> "Updated" ,
     _.textMsg -> "TextMsg" ,
     _.workflowId -> "AD_Workflow_ID" ,
     _.priority -> "Priority" ,
     _.recordId -> "Record_ID" ,
     _.tableId -> "AD_Table_ID" ,
     _.endWaitTime -> "EndWaitTime" ,
     _.dateLastAlert -> "DateLastAlert" ,
     _.dynPriorityStart -> "DynPriorityStart" ,
     _.uuid -> "UUID" )

     def wfActivityProperties: Seq[(String, String)] = wfActivitySchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
