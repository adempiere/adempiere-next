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

package org.eevolution.context.workflow.domain.service

import org.eevolution.context.workflow.domain.model.WfNode
import org.eevolution.context.workflow.domain.wfNodeRepositoryApi.WfNodeRepository
import org.eevolution.context.workflow.domain.wfNodeServiceApi.WfNodeService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.blocking.Blocking
import zio.RIO

/*
* Accessors method Trait for WfNode Domain Service 
*
* The accessor methods are provided so that we can build programs without 
* bothering about the implementation details of the required modules. 
* The compiler will fully infer all the required modules to complete the task. 
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeServiceApiAccessors {

  def getByWfNodeId(wfNodeId: Id): RIO[WfNodeService with WfNodeRepository, Option[WfNode]] =
   RIO.accessM(_.get.getByWfNodeId(wfNodeId))
   
  def getByName(name: String): RIO[WfNodeService with WfNodeRepository, Option[WfNode]] =
   RIO.accessM(_.get.getByName(name))
   
  def getByDescription(description: Option[String]): RIO[WfNodeService with WfNodeRepository, List[WfNode]] =
   RIO.accessM(_.get.getByDescription(description))
   
  def getByWorkflowId(workflowId: TableDirect): RIO[WfNodeService with WfNodeRepository, List[WfNode]] =
   RIO.accessM(_.get.getByWorkflowId(workflowId))
   
  def getByAttributeName(attributeName: Option[String]): RIO[WfNodeService with WfNodeRepository, List[WfNode]] =
   RIO.accessM(_.get.getByAttributeName(attributeName))
   
  def getByValue(value: String): RIO[WfNodeService with WfNodeRepository, Option[WfNode]] =
   RIO.accessM(_.get.getByValue(value))
   

  def getAll: RIO[WfNodeService with WfNodeRepository, List[WfNode]] =
    RIO.accessM(_.get.getAll)

  def getAllByClientId(tenantId: TableDirect): RIO[WfNodeService with WfNodeRepository, List[WfNode]] =
    RIO.accessM(_.get.getAllByClientId(tenantId))
    
    def save(wfNode: WfNode): RIO[WfNodeService with WfNodeRepository, WfNode] =
    RIO.accessM(_.get.save(wfNode))  
   
}