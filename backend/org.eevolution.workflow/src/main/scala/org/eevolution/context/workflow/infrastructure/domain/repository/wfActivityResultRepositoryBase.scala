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
import org.eevolution.context.workflow.domain.model.WfActivityResult
import org.eevolution.context.workflow.domain.wfActivityResultRepositoryApi.WfActivityResultRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfActivityResult Repository Base Trait with the implementation for WfActivityResult Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfActivityResult Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfActivityResultRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfActivityResultRepository.Service {

    import context._
  
		override def getByWfActivityId(wfActivityId: TableDirect): RIO[WfActivityResultRepository, List[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult].filter(_.wfActivityId == lift(wfActivityId)))
          )
        )
      )
      
		override def getByAttributeName(attributeName: String): RIO[WfActivityResultRepository, List[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult].filter(_.attributeName == lift(attributeName)))
          )
        )
      )
      
		override def getByWfActivityResultId(wfActivityResultId: Id): RIO[WfActivityResultRepository, Option[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult].filter(_.wfActivityResultId == lift(wfActivityResultId))).map(_.headOption)
          )
        )
      )
      
		override def getByDescription(description: Option[String]): RIO[WfActivityResultRepository, List[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult].filter(_.description == lift(description)))
          )
        )
      )
      
     override def getAll: RIO[WfActivityResultRepository, List[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfActivityResultRepository,List[WfActivityResult]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfActivityResult].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfActivityResult: WfActivityResult): RIO[Any, WfActivityResult] = RIO.fromTry(
       Try(EntityWrapper.save(wfActivityResult, wfActivityResultProperties)) 
    )
    
		implicit val wfActivityResultSchemaMeta : context.SchemaMeta[WfActivityResult] = 
     schemaMeta[WfActivityResult] ("AD_WF_ActivityResult",
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.wfActivityId -> "AD_WF_Activity_ID" ,
     _.attributeName -> "AttributeName" ,
     _.createdBy -> "CreatedBy" ,
     _.updatedBy -> "UpdatedBy" ,
     _.updated -> "Updated" ,
     _.isActive -> "IsActive" ,
     _.attributeValue -> "AttributeValue" ,
     _.created -> "Created" ,
     _.wfActivityResultId -> "AD_WF_ActivityResult_ID" ,
     _.description -> "Description" ,
     _.help -> "Help" ,
     _.uuid -> "UUID" )

     def wfActivityResultProperties: Seq[(String, String)] = wfActivityResultSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
