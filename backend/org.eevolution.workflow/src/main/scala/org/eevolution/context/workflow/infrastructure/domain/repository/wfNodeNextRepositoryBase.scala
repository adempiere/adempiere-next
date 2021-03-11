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
import org.eevolution.context.workflow.domain.model.WfNodeNext
import org.eevolution.context.workflow.domain.wfNodeNextRepositoryApi.WfNodeNextRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfNodeNext Repository Base Trait with the implementation for WfNodeNext Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfNodeNext Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeNextRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfNodeNextRepository.Service {

    import context._
  
		override def getByWfNodeId(wfNodeId: TableDirect): RIO[WfNodeNextRepository, List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.wfNodeId == lift(wfNodeId)))
          )
        )
      )
      
		override def getByWfNextId(wfNextId: Table): RIO[WfNodeNextRepository, List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.wfNextId == lift(wfNextId)))
          )
        )
      )
      
		override def getBySeqNo(seqNo: Int): RIO[WfNodeNextRepository, List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.seqNo == lift(seqNo)))
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfNodeNextRepository, List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByWfNodeNextId(wfNodeNextId: Id): RIO[WfNodeNextRepository, Option[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.wfNodeNextId == lift(wfNodeNextId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfNodeNextRepository, List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfNodeNextRepository,List[WfNodeNext]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodeNext].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfNodeNext: WfNodeNext): RIO[Any, WfNodeNext] = RIO.fromTry(
       Try(EntityWrapper.save(wfNodeNext, wfNodeNextProperties)) 
    )
    
		implicit val wfNodeNextSchemaMeta : context.SchemaMeta[WfNodeNext] = 
     schemaMeta[WfNodeNext] ("AD_WF_NodeNext",
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.isActive -> "IsActive" ,
     _.created -> "Created" ,
     _.createdBy -> "CreatedBy" ,
     _.updated -> "Updated" ,
     _.updatedBy -> "UpdatedBy" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.wfNextId -> "AD_WF_Next_ID" ,
     _.transitionCode -> "TransitionCode" ,
     _.entityType -> "EntityType" ,
     _.seqNo -> "SeqNo" ,
     _.description -> "Description" ,
     _.wfNodeNextId -> "AD_WF_NodeNext_ID" ,
     _.isStdUserWorkflow -> "IsStdUserWorkflow" ,
     _.uuid -> "UUID" )

     def wfNodeNextProperties: Seq[(String, String)] = wfNodeNextSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
