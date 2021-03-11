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
import org.eevolution.context.workflow.domain.model.WfNextCondition
import org.eevolution.context.workflow.domain.wfNextConditionRepositoryApi.WfNextConditionRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfNextCondition Repository Base Trait with the implementation for WfNextCondition Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfNextCondition Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNextConditionRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfNextConditionRepository.Service {

    import context._
  
		override def getByWfNextConditionId(wfNextConditionId: Id): RIO[WfNextConditionRepository, Option[WfNextCondition]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNextCondition].filter(_.wfNextConditionId == lift(wfNextConditionId))).map(_.headOption)
          )
        )
      )
      
		override def getByWfNodeNextId(wfNodeNextId: TableDirect): RIO[WfNextConditionRepository, List[WfNextCondition]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNextCondition].filter(_.wfNodeNextId == lift(wfNodeNextId)))
          )
        )
      )
      
		override def getByValue(value: String): RIO[WfNextConditionRepository, Option[WfNextCondition]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNextCondition].filter(_.value == lift(value))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfNextConditionRepository, List[WfNextCondition]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNextCondition])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfNextConditionRepository,List[WfNextCondition]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfNextCondition].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfNextCondition: WfNextCondition): RIO[Any, WfNextCondition] = RIO.fromTry(
       Try(EntityWrapper.save(wfNextCondition, wfNextConditionProperties)) 
    )
    
		implicit val wfNextConditionSchemaMeta : context.SchemaMeta[WfNextCondition] = 
     schemaMeta[WfNextCondition] ("AD_WF_NextCondition",
     _.createdBy -> "CreatedBy" ,
     _.wfNextConditionId -> "AD_WF_NextCondition_ID" ,
     _.andOr -> "AndOr" ,
     _.created -> "Created" ,
     _.operation -> "Operation" ,
     _.orgId -> "AD_Org_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.wfNodeNextId -> "AD_WF_NodeNext_ID" ,
     _.seqNo -> "SeqNo" ,
     _.value -> "Value" ,
     _.updatedBy -> "UpdatedBy" ,
     _.isActive -> "IsActive" ,
     _.value2 -> "Value2" ,
     _.entityType -> "EntityType" ,
     _.updated -> "Updated" ,
     _.columnId -> "AD_Column_ID" ,
     _.uuid -> "UUID" )

     def wfNextConditionProperties: Seq[(String, String)] = wfNextConditionSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
