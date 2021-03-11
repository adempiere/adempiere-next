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
import org.eevolution.context.workflow.domain.model.Workflow
import org.eevolution.context.workflow.domain.workflowRepositoryApi.WorkflowRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* Workflow Repository Base Trait with the implementation for Workflow Repository Implementation
*
* Is a contract to define the Repository Base Implementation for Workflow Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait workflowRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WorkflowRepository.Service {

    import context._
  
		override def getByName(name: String): RIO[WorkflowRepository, Option[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.name == lift(name))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WorkflowRepository, List[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByWorkflowId(workflowId: Id): RIO[WorkflowRepository, Option[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.workflowId == lift(workflowId))).map(_.headOption)
          )
        )
      )
      
		override def getByValue(value: String): RIO[WorkflowRepository, Option[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.value == lift(value))).map(_.headOption)
          )
        )
      )
      
		override def getByDocumentNo(documentNo: Option[String]): RIO[WorkflowRepository, List[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.documentNo == lift(documentNo)))
          )
        )
      )
      
     override def getAll: RIO[WorkflowRepository, List[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WorkflowRepository,List[Workflow]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[Workflow].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(workflow: Workflow): RIO[Any, Workflow] = RIO.fromTry(
       Try(EntityWrapper.save(workflow, workflowProperties)) 
    )
    
		implicit val workflowSchemaMeta : context.SchemaMeta[Workflow] = 
     schemaMeta[Workflow] ("AD_Workflow",
     _.name -> "Name" ,
     _.description -> "Description" ,
     _.help -> "Help" ,
     _.workflowId -> "AD_Workflow_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.isActive -> "IsActive" ,
     _.created -> "Created" ,
     _.createdBy -> "CreatedBy" ,
     _.updated -> "Updated" ,
     _.updatedBy -> "UpdatedBy" ,
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.accessLevel -> "AccessLevel" ,
     _.entityType -> "EntityType" ,
     _.author -> "Author" ,
     _.priority -> "Priority" ,
     _.workingTime -> "WorkingTime" ,
     _.limit -> "Limit" ,
     _.validFrom -> "ValidFrom" ,
     _.duration -> "Duration" ,
     _.wfResponsibleId -> "AD_WF_Responsible_ID" ,
     _.version -> "Version" ,
     _.cost -> "Cost" ,
     _.validTo -> "ValidTo" ,
     _.durationUnit -> "DurationUnit" ,
     _.waitingTime -> "WaitingTime" ,
     _.publishStatus -> "PublishStatus" ,
     _.workflowProcessorId -> "AD_WorkflowProcessor_ID" ,
     _.isDefault -> "IsDefault" ,
     _.validateWorkflow -> "ValidateWorkflow" ,
     _.tableId -> "AD_Table_ID" ,
     _.value -> "Value" ,
     _.workflowType -> "WorkflowType" ,
     _.docValueLogic -> "DocValueLogic" ,
     _.isValid -> "IsValid" ,
     _.resourceId -> "S_Resource_ID" ,
     _.setupTime -> "SetupTime" ,
     _.movingTime -> "MovingTime" ,
     _.processType -> "ProcessType" ,
     _.documentNo -> "DocumentNo" ,
     _.qtyBatchSize -> "QtyBatchSize" ,
     _.queuingTime -> "QueuingTime" ,
     _.isBetaFunctionality -> "IsBetaFunctionality" ,
     _.`yield` -> "Yield" ,
     _.unitsCycles -> "UnitsCycles" ,
     _.overlapUnits -> "OverlapUnits" ,
     _.uuid -> "UUID" )

     def workflowProperties: Seq[(String, String)] = workflowSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
