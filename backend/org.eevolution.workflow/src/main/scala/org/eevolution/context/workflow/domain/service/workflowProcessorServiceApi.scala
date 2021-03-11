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

import org.eevolution.context.workflow.domain.model.WorkflowProcessor
import org.eevolution.context.workflow.domain.workflowProcessorRepositoryApi.WorkflowProcessorRepository
import org.eevolution.context.workflow.domain.service.{
  workflowProcessorServiceApiAccessors,
  workflowProcessorServiceApiBase
}
import org.eevolution.context.kernel.domain.ubiquitouslanguage._
import zio.{Has, RIO, ZIO}

/*
* WorkflowProcessor Service Api Package Object for WorkflowProcessor Domain Service
*
* Is a contract to define the service api WorkflowProcessor Domain Service
* this class is generated from the Application Dictionary if it does not exist and 
* extends from the base contract class, the developer can add or modify the Api or create their own contract
*/
package object workflowProcessorServiceApi extends workflowProcessorServiceApiAccessors {

type WorkflowProcessorService = Has[WorkflowProcessorService.Service]

object WorkflowProcessorService {

     trait Service extends workflowProcessorServiceApiBase[WorkflowProcessorRepository] {

    }
  }
}  
