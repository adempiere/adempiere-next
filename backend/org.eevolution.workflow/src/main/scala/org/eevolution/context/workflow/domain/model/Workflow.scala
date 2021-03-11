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
* Workflow Algebraic Data Types domain model
* Workflow
* Workflow or combination of tasks
* 
*/

case class Workflow(
				name : String, //Name - Name - Alphanumeric identifier of the entity
				description : Option[String], //Description - Description - Optional short description of the record
				help : Option[String], //Help - Comment/Help - Comment or Hint
				workflowId : Id, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
				clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
				orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
				isActive : YesNo = true, //IsActive - Active - The record is active in the system
				created : DateTime, //Created - Created - Date this record was created
				createdBy : Table, //CreatedBy - Created By - User who created this records
				updated : DateTime, //Updated - Updated - Date this record was updated
				updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
				wfNodeId : Option[TableDirect], //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
				accessLevel : ListType, //AccessLevel - Data Access Level - Access Level required
				entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
				author : String, //Author - Author - Author/Creator of the Entity
				priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
				workingTime : Int, //WorkingTime - Working Time - Workflow Simulation Execution Time
				limit : Option[Int], //Limit - Duration Limit - Maximum Duration in Duration Unit
				validFrom : Option[DateTime], //ValidFrom - Valid from - Valid from including this date (first day)
				duration : Int, //Duration - Duration - Normal Duration in Duration Unit
				wfResponsibleId : Option[TableDirect], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
				version : Int, //Version - Version - Version of the table definition
				cost : CostPrice, //Cost - Cost - Cost information
				validTo : Option[DateTime], //ValidTo - Valid to - Valid to including this date (last day)
				durationUnit : Option[ListType], //DurationUnit - Duration Unit - Unit of Duration
				waitingTime : Int, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
				publishStatus : ListType = "U", //PublishStatus - Publication Status - Status of Publication
				workflowProcessorId : Option[TableDirect], //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
				isDefault : YesNo, //IsDefault - Default - Default value
				validateWorkflow : Option[String], //ValidateWorkflow - Validate Workflow - 
				tableId : Option[Search], //AD_Table_ID - Table - Database Table information
				value : String, //Value - Search Key - Search key for the record in the format required - must be unique
				workflowType : ListType = "G", //WorkflowType - Workflow Type - Type of Workflow
				docValueLogic : Option[String], //DocValueLogic - Document Value Logic - Logic to determine Workflow Start - If true, a workflow process is started for the document
				isValid : YesNo, //IsValid - Valid - Element is valid
				resourceId : Option[TableDirect], //S_Resource_ID - Resource - Resource
				setupTime : Option[Int], //SetupTime - Setup Time - Setup time before starting Production
				movingTime : Option[Int], //MovingTime - Moving Time - 
				processType : Option[ListType], //ProcessType - Process Type - 
				documentNo : Option[String], //DocumentNo - Document No - Document sequence number of the document
				qtyBatchSize : Option[Quantity] = Some(BigDecimal(0)), //QtyBatchSize - Qty Batch Size - 
				queuingTime : Option[Int], //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
				isBetaFunctionality : YesNo = false, //IsBetaFunctionality - Beta Functionality - This functionality is considered Beta
				`yield` : Option[Int], //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
				unitsCycles : Option[Number], //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
				overlapUnits : Option[Number], //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
				uuid : Option[String], //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
with ActiveEnabled
with Identifiable
with Traceable {
	override type ActiveEnabled = this.type
	override type Traceable = this.type
	override type Identifiable = this.type
	override val entityName: String = "AD_Workflow"
	override val identifier: String = "AD_Workflow_ID"
	override val Id: Id = workflowId
}


object Workflow {
	def create(
			name : String, //Name - Name - Alphanumeric identifier of the entity
			description : Option[String], //Description - Description - Optional short description of the record
			help : Option[String], //Help - Comment/Help - Comment or Hint
			workflowId : Id, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
			clientId : TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
			orgId : TableDirect, //AD_Org_ID - Organization - Organizational entity within client
			isActive : YesNo = true, //IsActive - Active - The record is active in the system
			created : DateTime, //Created - Created - Date this record was created
			createdBy : Table, //CreatedBy - Created By - User who created this records
			updated : DateTime, //Updated - Updated - Date this record was updated
			updatedBy : Table, //UpdatedBy - Updated By - User who updated this records
			wfNodeId : Option[TableDirect], //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
			accessLevel : ListType, //AccessLevel - Data Access Level - Access Level required
			entityType : String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
			author : String, //Author - Author - Author/Creator of the Entity
			priority : Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
			workingTime : Int, //WorkingTime - Working Time - Workflow Simulation Execution Time
			limit : Option[Int], //Limit - Duration Limit - Maximum Duration in Duration Unit
			validFrom : Option[DateTime], //ValidFrom - Valid from - Valid from including this date (first day)
			duration : Int, //Duration - Duration - Normal Duration in Duration Unit
			wfResponsibleId : Option[TableDirect], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
			version : Int, //Version - Version - Version of the table definition
			cost : CostPrice, //Cost - Cost - Cost information
			validTo : Option[DateTime], //ValidTo - Valid to - Valid to including this date (last day)
			durationUnit : Option[ListType], //DurationUnit - Duration Unit - Unit of Duration
			waitingTime : Int, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
			publishStatus : ListType = "U", //PublishStatus - Publication Status - Status of Publication
			workflowProcessorId : Option[TableDirect], //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
			isDefault : YesNo, //IsDefault - Default - Default value
			validateWorkflow : Option[String], //ValidateWorkflow - Validate Workflow - 
			tableId : Option[Search], //AD_Table_ID - Table - Database Table information
			value : String, //Value - Search Key - Search key for the record in the format required - must be unique
			workflowType : ListType = "G", //WorkflowType - Workflow Type - Type of Workflow
			docValueLogic : Option[String], //DocValueLogic - Document Value Logic - Logic to determine Workflow Start - If true, a workflow process is started for the document
			isValid : YesNo, //IsValid - Valid - Element is valid
			resourceId : Option[TableDirect], //S_Resource_ID - Resource - Resource
			setupTime : Option[Int], //SetupTime - Setup Time - Setup time before starting Production
			movingTime : Option[Int], //MovingTime - Moving Time - 
			processType : Option[ListType], //ProcessType - Process Type - 
			documentNo : Option[String], //DocumentNo - Document No - Document sequence number of the document
			qtyBatchSize : Option[Quantity] = Some(BigDecimal(0)), //QtyBatchSize - Qty Batch Size - 
			queuingTime : Option[Int], //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
			isBetaFunctionality : YesNo = false, //IsBetaFunctionality - Beta Functionality - This functionality is considered Beta
			`yield` : Option[Int], //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
			unitsCycles : Option[Number], //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
			overlapUnits : Option[Number], //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
			uuid : Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
	) : Workflow = Workflow(
						name, //Name - Name - Alphanumeric identifier of the entity
						description, //Description - Description - Optional short description of the record
						help, //Help - Comment/Help - Comment or Hint
						workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
						clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
						orgId, //AD_Org_ID - Organization - Organizational entity within client
						isActive, //IsActive - Active - The record is active in the system
						created, //Created - Created - Date this record was created
						createdBy, //CreatedBy - Created By - User who created this records
						updated, //Updated - Updated - Date this record was updated
						updatedBy, //UpdatedBy - Updated By - User who updated this records
						wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
						accessLevel, //AccessLevel - Data Access Level - Access Level required
						entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
						author, //Author - Author - Author/Creator of the Entity
						priority, //Priority - Priority - Indicates if this request is of a high, medium or low priority.
						workingTime, //WorkingTime - Working Time - Workflow Simulation Execution Time
						limit, //Limit - Duration Limit - Maximum Duration in Duration Unit
						validFrom, //ValidFrom - Valid from - Valid from including this date (first day)
						duration, //Duration - Duration - Normal Duration in Duration Unit
						wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
						version, //Version - Version - Version of the table definition
						cost, //Cost - Cost - Cost information
						validTo, //ValidTo - Valid to - Valid to including this date (last day)
						durationUnit, //DurationUnit - Duration Unit - Unit of Duration
						waitingTime, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
						publishStatus, //PublishStatus - Publication Status - Status of Publication
						workflowProcessorId, //AD_WorkflowProcessor_ID - Workflow Processor - Workflow Processor Server
						isDefault, //IsDefault - Default - Default value
						validateWorkflow, //ValidateWorkflow - Validate Workflow - 
						tableId, //AD_Table_ID - Table - Database Table information
						value, //Value - Search Key - Search key for the record in the format required - must be unique
						workflowType, //WorkflowType - Workflow Type - Type of Workflow
						docValueLogic, //DocValueLogic - Document Value Logic - Logic to determine Workflow Start - If true, a workflow process is started for the document
						isValid, //IsValid - Valid - Element is valid
						resourceId, //S_Resource_ID - Resource - Resource
						setupTime, //SetupTime - Setup Time - Setup time before starting Production
						movingTime, //MovingTime - Moving Time - 
						processType, //ProcessType - Process Type - 
						documentNo, //DocumentNo - Document No - Document sequence number of the document
						qtyBatchSize, //QtyBatchSize - Qty Batch Size - 
						queuingTime, //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
						isBetaFunctionality, //IsBetaFunctionality - Beta Functionality - This functionality is considered Beta
						`yield`, //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
						unitsCycles, //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
						overlapUnits, //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
						uuid)  //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
