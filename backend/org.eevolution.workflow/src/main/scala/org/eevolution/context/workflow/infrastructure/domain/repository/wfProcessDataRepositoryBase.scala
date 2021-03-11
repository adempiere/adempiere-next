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
import org.eevolution.context.workflow.domain.model.WfProcessData
import org.eevolution.context.workflow.domain.wfProcessDataRepositoryApi.WfProcessDataRepository
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import org.eevolution.context.kernel.domain.util.EntityWrapper
import zio.RIO
import zio.blocking.Blocking

import scala.util.Try
/* 
* WfProcessData Repository Base Trait with the implementation for WfProcessData Repository Implementation
*
* Is a contract to define the Repository Base Implementation for WfProcessData Repository
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfProcessDataRepositoryBase {

  abstract class Service[Dialect <: SqlIdiom, Naming <: NamingStrategy](
    val context: JdbcContext[Dialect, Naming],
    val blocking: Blocking.Service
  ) extends WfProcessDataRepository.Service {

    import context._
  
		override def getByWfProcessId(wfProcessId: TableDirect): RIO[WfProcessDataRepository, List[WfProcessData]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcessData].filter(_.wfProcessId == lift(wfProcessId)))
          )
        )
      )
      
		override def getByAttributeName(attributeName: String): RIO[WfProcessDataRepository, List[WfProcessData]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcessData].filter(_.attributeName == lift(attributeName)))
          )
        )
      )
      
		override def getByWfProcessDataId(wfProcessDataId: Id): RIO[WfProcessDataRepository, Option[WfProcessData]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcessData].filter(_.wfProcessDataId == lift(wfProcessDataId))).map(_.headOption)
          )
        )
      )
      
     override def getAll: RIO[WfProcessDataRepository, List[WfProcessData]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcessData])
          )
        )
      )

     override def getAllByClientId(tenantId: TableDirect): RIO[WfProcessDataRepository,List[WfProcessData]] =
      blocking.blocking(
        RIO(
          performIO(
            runIO(query[WfProcessData].filter(_.clientId == lift(tenantId)))
          )
        )
      )
    
    override def save(wfProcessData: WfProcessData): RIO[Any, WfProcessData] = RIO.fromTry(
       Try(EntityWrapper.save(wfProcessData, wfProcessDataProperties)) 
    )
    
		implicit val wfProcessDataSchemaMeta : context.SchemaMeta[WfProcessData] = 
     schemaMeta[WfProcessData] ("AD_WF_ProcessData",
     _.wfProcessId -> "AD_WF_Process_ID" ,
     _.attributeName -> "AttributeName" ,
     _.isActive -> "IsActive" ,
     _.createdBy -> "CreatedBy" ,
     _.updatedBy -> "UpdatedBy" ,
     _.wfProcessDataId -> "AD_WF_ProcessData_ID" ,
     _.clientId -> "AD_Client_ID" ,
     _.orgId -> "AD_Org_ID" ,
     _.updated -> "Updated" ,
     _.attributeValue -> "AttributeValue" ,
     _.created -> "Created" ,
     _.uuid -> "UUID" )

     def wfProcessDataProperties: Seq[(String, String)] = wfProcessDataSchemaMeta.entity.ast.quat.renames.toSeq  
    }
}        
