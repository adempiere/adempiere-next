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

package org.eevolution.context.workflow.domain.model

import org.eevolution.context.kernel.domain.model._
import org.eevolution.context.kernel.domain.ubiquitouslanguage._

/**
* WorkflowProcessorLog Algebraic Data Types domain model
* Workflow Processorl Log
* Result of the execution of the Workflow Processor
* 
*/

case class WorkflowProcessorLog(
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				workflowProcessorId : TableDirect, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
				workflowProcessorLogId : Id, //AD_WorkflowProcessorLog_ID - Workflow Processorl Log - Result of the execution of the Workflow Processor
				binaryData : Option[Array[Byte]], //BinaryData - Binary Data - Binary Data
				updated : DateTime, //Updated - Updated - Date this record was updated
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				created : DateTime, //Created - Created - Date this record was created
				summary : Option[String], //Summary - Summary - Textual summary of this request
				textMsg : Option[String], //TextMsg - Text Message - Text Message
				reference : Option[String], //Reference - Reference - Reference for this record
				description : Option[String], //Description - Description - Optional short description of the record
				isError : YesNo, //IsError - Error - An Error occurred in the execution
				createdBy : Table, //CreatedBy - Created By - User who created this records
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_WorkflowProcessorLog"
	override val identifier: String = "AD_WorkflowProcessorLog_ID"
	override val Id: Id = workflowProcessorLogId
}


object WorkflowProcessorLog {
	def create(
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			workflowProcessorId : TableDirect, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
			workflowProcessorLogId : Id, //AD_WorkflowProcessorLog_ID - Workflow Processorl Log - Result of the execution of the Workflow Processor
			binaryData : Option[Array[Byte]], //BinaryData - Binary Data - Binary Data
			updated : DateTime, //Updated - Updated - Date this record was updated
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			created : DateTime, //Created - Created - Date this record was created
			summary : Option[String], //Summary - Summary - Textual summary of this request
			textMsg : Option[String], //TextMsg - Text Message - Text Message
			reference : Option[String], //Reference - Reference - Reference for this record
			description : Option[String], //Description - Description - Optional short description of the record
			isError : YesNo, //IsError - Error - An Error occurred in the execution
			createdBy : Table, //CreatedBy - Created By - User who created this records
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : WorkflowProcessorLog = WorkflowProcessorLog(
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						isActive, //IsActive - Active - The record is active in the system
						workflowProcessorId, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
						workflowProcessorLogId, //AD_WorkflowProcessorLog_ID - Workflow Processorl Log - Result of the execution of the Workflow Processor
						binaryData, //BinaryData - Binary Data - Binary Data
						updated, //Updated - Updated - Date this record was updated
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						created, //Created - Created - Date this record was created
						summary, //Summary - Summary - Textual summary of this request
						textMsg, //TextMsg - Text Message - Text Message
						reference, //Reference - Reference - Reference for this record
						description, //Description - Description - Optional short description of the record
						isError, //IsError - Error - An Error occurred in the execution
						createdBy, //CreatedBy - Created By - User who created this records
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
