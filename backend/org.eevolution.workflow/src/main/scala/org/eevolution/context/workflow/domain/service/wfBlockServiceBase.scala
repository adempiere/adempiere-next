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

import org.eevolution.context.workflow.domain.model._
import org.eevolution.context.workflow.domain.wfBlockRepositoryApi.WfBlockRepository
import org.eevolution.context.workflow.domain.wfBlockServiceApi.WfBlockService
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.RIO

/*
* WfBlock Service Base Trait with the implementation for WfBlock Domain Service Implementation
*
* Is a contract to define the Service Base Implementation for WfBlock Domain Service
* This class should not be modified since it is generated from the Application Dictionary 
* maybe any change made manually will be lost.
*/
trait wfBlockServiceBase {

  abstract class Service(wfBlockRepository: WfBlockRepository.Service)
    extends WfBlockService.Service {

      override def getByDescription(
					description: Option[String]
			): RIO[WfBlockRepository, List[WfBlock]] = 
				wfBlockRepository.getByDescription(description)

      override def getByWorkflowId(
					workflowId: TableDirect
			): RIO[WfBlockRepository, List[WfBlock]] = 
				wfBlockRepository.getByWorkflowId(workflowId)

      override def getByName(
					name: String
			): RIO[WfBlockRepository, Option[WfBlock]] = 
				wfBlockRepository.getByName(name)

      override def getByWfBlockId(
					wfBlockId: Id
			): RIO[WfBlockRepository, Option[WfBlock]] = 
				wfBlockRepository.getByWfBlockId(wfBlockId)

    override def getAll: RIO[WfBlockRepository, List[WfBlock]] = 
      wfBlockRepository.getAll
    
      override def getAllByClientId(tenantId: TableDirect): RIO[WfBlockRepository, List[WfBlock]] = 
      wfBlockRepository.getAllByClientId(tenantId)    
      
      override def save(wfBlock: WfBlock): RIO[WfBlockRepository, WfBlock] = 
      wfBlockRepository.save(wfBlock)   
      
  }
}
