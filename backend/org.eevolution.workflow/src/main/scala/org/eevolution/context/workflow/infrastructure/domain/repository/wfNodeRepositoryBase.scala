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
import org.eevolution.context.workflow.domain.model.WfNode
import org.eevolution.context.workflow.domain.wfNodeRepositoryApi.WfNodeRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfNode Repository Base Trait with the implementation for WfNode Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfNode Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfNodeRepository.Service {

    import context._
  
		override def getByWfNodeId(wfNodeId: Id): RIO[WfNodeRepository, Option[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.wfNodeId == lift(wfNodeId))).map(_.headOption)
          )
        )
      )
      
		override def getByName(name: String): RIO[WfNodeRepository, Option[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.name == lift(name))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfNodeRepository, List[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByWorkflowId(workflowId: TableDirect): RIO[WfNodeRepository, List[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.workflowId == lift(workflowId)))
          )
        )
      )
      
		override def getByAttributeName(attributeName: Option[String]): RIO[WfNodeRepository, List[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.attributeName == lift(attributeName)))
          )
        )
      )
      
		override def getByValue(value: String): RIO[WfNodeRepository, Option[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.value == lift(value))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfNodeRepository, List[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfNodeRepository,List[WfNode]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNode].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfNode: WfNode): RIO[Any, WfNode] = RIO.fromTry(
       Try(EntityWrapper.save(wfNode, wfNodeProperties)) 
    )
    
		implicit val wfNodeSchemaMeta : context.SchemaMeta[WfNode] = 
     schemaMeta[WfNode] ("AD_WF_Node",
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.name -> "Name" ,
     _.description -> "Description" ,
     _.workflowId -> "AD_Workflow_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.isActive -> "IsActive" ,
     _.created -> "Created" ,
     _.createdBy -> "CreatedBy" ,
     _.updated -> "Updated" ,
     _.updatedBy -> "UpdatedBy" ,
     _.help -> "Help" ,
     _.action -> "Action" ,
     _.windowId -> "AD_Window_ID" ,
     _.workflowId -> "Workflow_ID" ,
     _.taskId -> "AD_Task_ID" ,
     _.processId -> "AD_Process_ID" ,
     _.formId -> "AD_Form_ID" ,
     _.isCentrallyMaintained -> "IsCentrallyMaintained" ,
     _.yPosition -> "YPosition" ,
     _.entityType -> "EntityType" ,
     _.xPosition -> "XPosition" ,
     _.limit -> "Limit" ,
     _.startMode -> "StartMode" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.duration -> "Duration" ,
     _.subflowExecution -> "SubflowExecution" ,
     _.cost -> "Cost" ,
     _.waitingTime -> "WaitingTime" ,
     _.workingTime -> "WorkingTime" ,
     _.wfBlockId -> "AD_WF_Block_ID" ,
     _.priority -> "Priority" ,
     _.finishMode -> "FinishMode" ,
     _.joinElement -> "JoinElement" ,
     _.splitElement -> "SplitElement" ,
     _.imageId -> "AD_Image_ID" ,
     _.columnId -> "AD_Column_ID" ,
     _.waitTime -> "WaitTime" ,
     _.attributeValue -> "AttributeValue" ,
     _.attributeName -> "AttributeName" ,
     _.docAction -> "DocAction" ,
     _.value -> "Value" ,
     _.dynPriorityUnit -> "DynPriorityUnit" ,
     _.dynPriorityChange -> "DynPriorityChange" ,
     _.eMailRecipient -> "EMailRecipient" ,
     _.eMail -> "EMail" ,
     _.mailTextId -> "R_MailText_ID" ,
     _.validTo -> "ValidTo" ,
     _.isMilestone -> "IsMilestone" ,
     _.isSubcontracting -> "IsSubcontracting" ,
     _.unitsCycles -> "UnitsCycles" ,
     _.movingTime -> "MovingTime" ,
     _.overlapUnits -> "OverlapUnits" ,
     _.bPartnerId -> "C_BPartner_ID" ,
     _.queuingTime -> "QueuingTime" ,
     _.resourceId -> "S_Resource_ID" ,
     _.setupTime -> "SetupTime" ,
     _.validFrom -> "ValidFrom" ,
     _.`yield` -> "Yield" ,
     _.viewId -> "AD_View_ID" ,
     _.browseId -> "AD_Browse_ID" ,
     _.uuid -> "UUID" )

     def wfNodeProperties: Seq[(String, String)] = wfNodeSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
