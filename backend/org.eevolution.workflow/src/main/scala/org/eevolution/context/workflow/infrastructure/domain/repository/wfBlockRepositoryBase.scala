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
import org.eevolution.context.workflow.domain.model.WfBlock
import org.eevolution.context.workflow.domain.wfBlockRepositoryApi.WfBlockRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfBlock Repository Base Trait with the implementation for WfBlock Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfBlock Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfBlockRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfBlockRepository.Service {

    import context._
  
		override def getByDescription(description: Option[String]): RIO[WfBlockRepository, List[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByWorkflowId(workflowId: TableDirect): RIO[WfBlockRepository, List[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock].filter(_.workflowId == lift(workflowId)))
          )
        )
      )
      
		override def getByName(name: String): RIO[WfBlockRepository, Option[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock].filter(_.name == lift(name))).map(_.headOption)
          )
        )
      )
      
		override def getByWfBlockId(wfBlockId: Id): RIO[WfBlockRepository, Option[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock].filter(_.wfBlockId == lift(wfBlockId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfBlockRepository, List[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfBlockRepository,List[WfBlock]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfBlock].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfBlock: WfBlock): RIO[Any, WfBlock] = RIO.fromTry(
       Try(EntityWrapper.save(wfBlock, wfBlockProperties)) 
    )
    
		implicit val wfBlockSchemaMeta : context.SchemaMeta[WfBlock] = 
     schemaMeta[WfBlock] ("AD_WF_Block",
     _.description -> "Description" ,
     _.clientId -> "AD_Client_ID" ,
     _.workflowId -> "AD_Workflow_ID" ,
     _.updated -> "Updated" ,
     _.createdBy -> "CreatedBy" ,
     _.isActive -> "IsActive" ,
     _.name -> "Name" ,
     _.created -> "Created" ,
     _.orgId -> "AD_Org_ID" ,
     _.wfBlockId -> "AD_WF_Block_ID" ,
     _.updatedBy -> "UpdatedBy" ,
     _.uuid -> "UUID" )

     def wfBlockProperties: Seq[(String, String)] = wfBlockSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
