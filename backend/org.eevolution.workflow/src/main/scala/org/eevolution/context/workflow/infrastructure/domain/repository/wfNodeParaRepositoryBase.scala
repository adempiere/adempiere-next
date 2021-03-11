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
import org.eevolution.context.workflow.domain.model.WfNodePara
import org.eevolution.context.workflow.domain.wfNodeParaRepositoryApi.WfNodeParaRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfNodePara Repository Base Trait with the implementation for WfNodePara Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfNodePara Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeParaRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfNodeParaRepository.Service {

    import context._
  
		override def getByWfNodeId(wfNodeId: TableDirect): RIO[WfNodeParaRepository, List[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara].filter(_.wfNodeId == lift(wfNodeId)))
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfNodeParaRepository, List[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara].filter(_.description == lift(description)))
          )
        )
      )
      
		override def getByAttributeName(attributeName: Option[String]): RIO[WfNodeParaRepository, List[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara].filter(_.attributeName == lift(attributeName)))
          )
        )
      )
      
		override def getByWfNodeParaId(wfNodeParaId: Id): RIO[WfNodeParaRepository, Option[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara].filter(_.wfNodeParaId == lift(wfNodeParaId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfNodeParaRepository, List[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfNodeParaRepository,List[WfNodePara]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNodePara].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfNodePara: WfNodePara): RIO[Any, WfNodePara] = RIO.fromTry(
       Try(EntityWrapper.save(wfNodePara, wfNodeParaProperties)) 
    )
    
		implicit val wfNodeParaSchemaMeta : context.SchemaMeta[WfNodePara] = 
     schemaMeta[WfNodePara] ("AD_WF_Node_Para",
     _.wfNodeId -> "AD_WF_Node_ID" ,
     _.createdBy -> "CreatedBy" ,
     _.created -> "Created" ,
     _.description -> "Description" ,
     _.attributeName -> "AttributeName" ,
     _.wfNodeParaId -> "AD_WF_Node_Para_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.processParaId -> "AD_Process_Para_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.updatedBy -> "UpdatedBy" ,
     _.isActive -> "IsActive" ,
     _.updated -> "Updated" ,
     _.attributeValue -> "AttributeValue" ,
     _.entityType -> "EntityType" ,
     _.uuid -> "UUID" )

     def wfNodeParaProperties: Seq[(String, String)] = wfNodeParaSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
