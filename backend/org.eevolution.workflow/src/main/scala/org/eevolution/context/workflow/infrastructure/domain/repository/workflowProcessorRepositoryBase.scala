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
import org.eevolution.context.workflow.domain.model.WorkflowProcessor
import org.eevolution.context.workflow.domain.workflowProcessorRepositoryApi.WorkflowProcessorRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WorkflowProcessor Repository Base Trait with the implementation for WorkflowProcessor Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WorkflowProcessor Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait workflowProcessorRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WorkflowProcessorRepository.Service {

    import context._
  
		override def getByDescription(description: Option[String]): RIO[WorkflowProcessorRepository, List[WorkflowProcessor]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessor].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByName(name: String): RIO[WorkflowProcessorRepository, Option[WorkflowProcessor]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessor].filter(_.name == lift(name))).map(_.headOption)
          )
        )
      )
      
		override def getByWorkflowProcessorId(workflowProcessorId: Id): RIO[WorkflowProcessorRepository, Option[WorkflowProcessor]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessor].filter(_.workflowProcessorId == lift(workflowProcessorId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WorkflowProcessorRepository, List[WorkflowProcessor]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessor])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WorkflowProcessorRepository,List[WorkflowProcessor]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowProcessor].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(workflowProcessor: WorkflowProcessor): RIO[Any, WorkflowProcessor] = RIO.fromTry(
       Try(EntityWrapper.save(workflowProcessor, workflowProcessorProperties)) 
    )
    
		implicit val workflowProcessorSchemaMeta : context.SchemaMeta[WorkflowProcessor] = 
     schemaMeta[WorkflowProcessor] ("AD_WorkflowProcessor",
     _.dateNextRun -> "DateNextRun" ,
     _.description -> "Description" ,
     _.keepLogDays -> "KeepLogDays" ,
     _.frequencyType -> "FrequencyType" ,
     _.name -> "Name" ,
     _.processing -> "Processing" ,
     _.updatedBy -> "UpdatedBy" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.workflowProcessorId -> "AD_WorkflowProcessor_ID" ,
     _.isActive -> "IsActive" ,
     _.createdBy -> "CreatedBy" ,
     _.frequency -> "Frequency" ,
     _.updated -> "Updated" ,
     _.created -> "Created" ,
     _.supervisorId -> "Supervisor_ID" ,
     _.dateLastRun -> "DateLastRun" ,
     _.inactivityAlertDays -> "InactivityAlertDays" ,
     _.remindDays -> "RemindDays" ,
     _.alertOverPriority -> "AlertOverPriority" ,
     _.uuid -> "UUID" )

     def workflowProcessorProperties: Seq[(String, String)] = workflowProcessorSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
