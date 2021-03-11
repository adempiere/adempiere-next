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
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfNode Service Api Base Trait for WfNode Domain Service
*
* Is a contract to define the repository api base WfNode Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfNodeServiceApiBase[-R] {
   def getByWfNodeId(wfNodeId: Id): RIO[R, Option[WfNode]]

   def getByName(name: String): RIO[R, Option[WfNode]]

   def getByDescription(description: Option[String]): RIO[R, List[WfNode]]

   def getByWorkflowId(workflowId: TableDirect): RIO[R, List[WfNode]]

   def getByAttributeName(attributeName: Option[String]): RIO[R, List[WfNode]]

   def getByValue(value: String): RIO[R, Option[WfNode]]

    def getAll: RIO[R, List[WfNode]]
    
    def getAllByClientId(tenantId: TableDirect): RIO[R, List[WfNode]]
    
    def save(wfNode: WfNode): RIO[R, WfNode]
}