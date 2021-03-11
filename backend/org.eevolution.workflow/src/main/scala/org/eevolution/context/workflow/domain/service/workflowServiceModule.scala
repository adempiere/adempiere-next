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

package org.eevolution.context.workflow.domain

import org.eevolution.context.workflow.domain.model.Workflow
import org.eevolution.context.workflow.domain.workflowRepositoryApi.WorkflowRepository
import org.eevolution.context.workflow.domain.workflowServiceApi.WorkflowService
import org.eevolution.context.workflow.domain.service.workflowServiceBase
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.{ RIO, ZLayer }

/*
* Workflow Service Module Package Object with the implementation for Workflow Domain Service
*
* Default implementation to Workflow Domain Service Base
* this class is generated from the Application Dictionary if it does not exist and 
* extends from the base contract class, the developer can add or modify the Api or create their own contract
*/
package object workflowServiceModule extends workflowServiceBase {

  case class Live(workflowRepository: WorkflowRepository.Service) extends Service(workflowRepository) {
  
  }
  
  object WorkflowServiceLayer {

    def live: ZLayer[WorkflowRepository, Nothing, WorkflowService] = ZLayer.fromService[
      WorkflowRepository.Service,
      WorkflowService.Service
    ](workflowRepository => Live(workflowRepository))

  }
}
  
