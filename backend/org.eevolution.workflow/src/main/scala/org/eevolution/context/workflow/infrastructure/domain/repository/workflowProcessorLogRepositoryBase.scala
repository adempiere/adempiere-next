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
import org.eevolution.context.workflow.domain.model.WorkflowProcessorLog
import org.eevolution.context.workflow.domain.workflowProcessorLogRepositoryApi.WorkflowProcessorLogRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WorkflowProcessorLog Repository Base Trait with the implementation for WorkflowProcessorLog Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WorkflowProcessorLog Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait workflowProcessorLogRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WorkflowProcessorLogRepository.Service {

    import context._
  
		override def getByWorkflowProcessorId(workflowProcessorId: TableDirect): RIO[WorkflowProcessorLogRepository, List[WorkflowProcessorLog]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessorLog].filter(_.workflowProcessorId == lift(workflowProcessorId)))
          )
        )
      )
      
		override def getByWorkflowProcessorLogId(workflowProcessorLogId: Id): RIO[WorkflowProcessorLogRepository, Option[WorkflowProcessorLog]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessorLog].filter(_.workflowProcessorLogId == lift(workflowProcessorLogId))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WorkflowProcessorLogRepository, List[WorkflowProcessorLog]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessorLog].filter(_.description == lift(description)))
          )
        )
      )
      
     override def getAll: RIO[WorkflowProcessorLogRepository, List[WorkflowProcessorLog]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessorLog])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WorkflowProcessorLogRepository,List[WorkflowProcessorLog]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessorLog].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(workflowProcessorLog: WorkflowProcessorLog): RIO[Any, WorkflowProcessorLog] = RIO.fromTry(
       Try(EntityWrapper.save(workflowProcessorLog, workflowProcessorLogProperties)) 
    )
    
		implicit val workflowProcessorLogSchemaMeta : context.SchemaMeta[WorkflowProcessorLog] = 
     schemaMeta[WorkflowProcessorLog] ("AD_WorkflowProcessorLog",
     _.orgId -> "AD_Org_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.isActive -> "IsActive" ,
     _.workflowProcessorId -> "AD_WorkflowProcessor_ID" ,
     _.workflowProcessorLogId -> "AD_WorkflowProcessorLog_ID" ,
     _.binaryData -> "BinaryData" ,
     _.updated -> "Updated" ,
     _.updatedBy -> "UpdatedBy" ,
     _.created -> "Created" ,
     _.summary -> "Summary" ,
     _.textMsg -> "TextMsg" ,
     _.reference -> "Reference" ,
     _.description -> "Description" ,
     _.isError -> "IsError" ,
     _.createdBy -> "CreatedBy" ,
     _.uuid -> "UUID" )

     def workflowProcessorLogProperties: Seq[(String, String)] = workflowProcessorLogSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
