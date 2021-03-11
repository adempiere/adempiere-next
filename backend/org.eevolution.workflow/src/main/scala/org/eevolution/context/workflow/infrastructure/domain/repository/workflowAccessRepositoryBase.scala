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
import org.eevolution.context.workflow.domain.model.WorkflowAccess
import org.eevolution.context.workflow.domain.workflowAccessRepositoryApi.WorkflowAccessRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WorkflowAccess Repository Base Trait with the implementation for WorkflowAccess Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WorkflowAccess Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait workflowAccessRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WorkflowAccessRepository.Service {

    import context._
  
     override def getAll: RIO[WorkflowAccessRepository, List[WorkflowAccess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowAccess])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WorkflowAccessRepository,List[WorkflowAccess]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WorkflowAccess].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(workflowAccess: WorkflowAccess): RIO[Any, WorkflowAccess] = RIO.fromTry(
       Try(EntityWrapper.save(workflowAccess, workflowAccessProperties)) 
    )
    
		implicit val workflowAccessSchemaMeta : context.SchemaMeta[WorkflowAccess] = 
     schemaMeta[WorkflowAccess] ("AD_Workflow_Access",
     _.workflowId -> "AD_Workflow_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.isActive -> "IsActive" ,
     _.created -> "Created" ,
     _.createdBy -> "CreatedBy" ,
     _.updated -> "Updated" ,
     _.updatedBy -> "UpdatedBy" ,
     _.isReadWrite -> "IsReadWrite" ,
     _.roleId -> "AD_Role_ID" ,
     _.uuid -> "UUID" )

     def workflowAccessProperties: Seq[(String, String)] = workflowAccessSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
