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
  * WfNode Algebraic Data Types domain model
  * Node
  * Workflow Node (activity) step or process
  *
  */

case class WfNode(
    wfNodeId: Id, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
    name: String, //Name - Name - Alphanumeric identifier of the entity
    description: Option[
      String
    ], //Description - Description - Optional short description of the record
    workflowId: TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
    clientId: TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
    orgId: TableDirect, //AD_Org_ID - Organization - Organizational entity within client
    isActive: YesNo =
      true, //IsActive - Active - The record is active in the system
    created: DateTime, //Created - Created - Date this record was created
    createdBy: Table, //CreatedBy - Created By - User who created this records
    updated: DateTime, //Updated - Updated - Date this record was updated
    updatedBy: Table, //UpdatedBy - Updated By - User who updated this records
    help: Option[String], //Help - Comment/Help - Comment or Hint
    action: ListType =
      "Z", //Action - Action - Indicates the Action to be performed
    windowId: Option[
      TableDirect
    ], //AD_Window_ID - Window - Data entry or display window
    refWorkflowId: Option[Table], //Workflow_ID - Workflow - Workflow or tasks
    taskId: Option[TableDirect], //AD_Task_ID - OS Task - Operation System Task
    processId: Option[
      TableDirect
    ], //AD_Process_ID - Process - Process or Report
    formId: Option[TableDirect], //AD_Form_ID - Special Form - Special Form
    isCentrallyMaintained: YesNo =
      true, //IsCentrallyMaintained - Centrally maintained - Information maintained in System Element table
    yPosition: Int, //YPosition - Y Position - Absolute Y (vertical) position in 1/72 of an inch
    entityType: String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
    xPosition: Int, //XPosition - X Position - Absolute X (horizontal) position in 1/72 of an inch
    limit: Int, //Limit - Duration Limit - Maximum Duration in Duration Unit
    startMode: Option[
      ListType
    ], //StartMode - Start Mode - Workflow Activity Start Mode
    wfResponsibleId: Option[
      TableDirect
    ], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
    duration: Int, //Duration - Duration - Normal Duration in Duration Unit
    subflowExecution: Option[
      ListType
    ], //SubflowExecution - Subflow Execution - Mode how the sub-workflow is executed
    cost: CostPrice, //Cost - Cost - Cost information
    waitingTime: Int, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
    workingTime: Option[
      Int
    ], //WorkingTime - Working Time - Workflow Simulation Execution Time
    wfBlockId: Option[
      TableDirect
    ], //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
    priority: Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
    finishMode: Option[
      ListType
    ], //FinishMode - Finish Mode - Workflow Activity Finish Mode
    joinElement: ListType =
      "X", //JoinElement - Join Element - Semantics for multiple incoming Transitions
    splitElement: ListType =
      "X", //SplitElement - Split Element - Semantics for multiple outgoing Transitions
    imageId: Option[TableDirect], //AD_Image_ID - Image - Image or Icon
    columnId: Option[TableDirect], //AD_Column_ID - Column - Column in the table
    waitTime: Option[
      Int
    ], //WaitTime - Wait Time - Time in minutes to wait (sleep)
    attributeValue: Option[
      String
    ], //AttributeValue - Attribute Value - Value of the Attribute
    attributeName: Option[
      String
    ], //AttributeName - Attribute Name - Name of the Attribute
    docAction: Option[ListType] =
      Option(
        "CO"
      ), //DocAction - Document Action - The targeted status of the document
    value: String, //Value - Search Key - Search key for the record in the format required - must be unique
    dynPriorityUnit: Option[
      ListType
    ], //DynPriorityUnit - Dynamic Priority Unit - Change of priority when Activity is suspended waiting for user
    dynPriorityChange: Option[
      Number
    ], //DynPriorityChange - Dynamic Priority Change - Change of priority when Activity is suspended waiting for user
    eMailRecipient: Option[
      ListType
    ], //EMailRecipient - EMail Recipient - Recipient of the EMail
    eMail: Option[String], //EMail - EMail Address - Electronic Mail Address
    mailTextId: Option[
      TableDirect
    ], //R_MailText_ID - Mail Template - Text templates for mailings
    validTo: Option[
      DateTime
    ], //ValidTo - Valid to - Valid to including this date (last day)
    isMilestone: Option[YesNo] = Option(false), //IsMilestone - Is Milestone -
    isSubcontracting: Option[YesNo] =
      Option(false), //IsSubcontracting - Is Subcontracting -
    unitsCycles: Option[Number] =
      Some(
        0
      ), //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
    movingTime: Option[Int], //MovingTime - Moving Time -
    overlapUnits: Option[
      Int
    ], //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
    bPartnerId: Option[
      Search
    ], //C_BPartner_ID - Business Partner  - Identifies a Business Partner
    queuingTime: Option[
      Int
    ], //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
    resourceId: Option[TableDirect], //S_Resource_ID - Resource - Resource
    setupTime: Option[
      Int
    ], //SetupTime - Setup Time - Setup time before starting Production
    validFrom: Option[
      DateTime
    ], //ValidFrom - Valid from - Valid from including this date (first day)
    `yield`: Option[Int], //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
    viewId: Option[
      TableDirect
    ], //AD_View_ID - View - View allows you to create dynamic views of information from the dictionary application
    browseId: Option[TableDirect], //AD_Browse_ID - Smart Browse -
    uuid: Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
) extends DomainModel
    with ActiveEnabled
    with Identifiable
    with Traceable {
  override type ActiveEnabled = this.type
  override type Traceable = this.type
  override type Identifiable = this.type
  override val entityName: String = "AD_WF_Node"
  override val identifier: String = "AD_WF_Node_ID"
  override val Id: Id = wfNodeId
}

object WfNode {
  def create(
      wfNodeId: Id, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
      name: String, //Name - Name - Alphanumeric identifier of the entity
      description: Option[
        String
      ], //Description - Description - Optional short description of the record
      workflowId: TableDirect, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
      clientId: TableDirect, //AD_Client_ID - Client - Client/Tenant for this installation.
      orgId: TableDirect, //AD_Org_ID - Organization - Organizational entity within client
      isActive: YesNo =
        true, //IsActive - Active - The record is active in the system
      created: DateTime, //Created - Created - Date this record was created
      createdBy: Table, //CreatedBy - Created By - User who created this records
      updated: DateTime, //Updated - Updated - Date this record was updated
      updatedBy: Table, //UpdatedBy - Updated By - User who updated this records
      help: Option[String], //Help - Comment/Help - Comment or Hint
      action: ListType =
        "Z", //Action - Action - Indicates the Action to be performed
      windowId: Option[
        TableDirect
      ], //AD_Window_ID - Window - Data entry or display window
      refWorkflowId: Option[Table], //Workflow_ID - Workflow - Workflow or tasks
      taskId: Option[
        TableDirect
      ], //AD_Task_ID - OS Task - Operation System Task
      processId: Option[
        TableDirect
      ], //AD_Process_ID - Process - Process or Report
      formId: Option[TableDirect], //AD_Form_ID - Special Form - Special Form
      isCentrallyMaintained: YesNo =
        true, //IsCentrallyMaintained - Centrally maintained - Information maintained in System Element table
      yPosition: Int, //YPosition - Y Position - Absolute Y (vertical) position in 1/72 of an inch
      entityType: String, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
      xPosition: Int, //XPosition - X Position - Absolute X (horizontal) position in 1/72 of an inch
      limit: Int, //Limit - Duration Limit - Maximum Duration in Duration Unit
      startMode: Option[
        ListType
      ], //StartMode - Start Mode - Workflow Activity Start Mode
      wfResponsibleId: Option[
        TableDirect
      ], //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
      duration: Int, //Duration - Duration - Normal Duration in Duration Unit
      subflowExecution: Option[
        ListType
      ], //SubflowExecution - Subflow Execution - Mode how the sub-workflow is executed
      cost: CostPrice, //Cost - Cost - Cost information
      waitingTime: Int, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
      workingTime: Option[
        Int
      ], //WorkingTime - Working Time - Workflow Simulation Execution Time
      wfBlockId: Option[
        TableDirect
      ], //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
      priority: Option[Int], //Priority - Priority - Indicates if this request is of a high, medium or low priority.
      finishMode: Option[
        ListType
      ], //FinishMode - Finish Mode - Workflow Activity Finish Mode
      joinElement: ListType =
        "X", //JoinElement - Join Element - Semantics for multiple incoming Transitions
      splitElement: ListType =
        "X", //SplitElement - Split Element - Semantics for multiple outgoing Transitions
      imageId: Option[TableDirect], //AD_Image_ID - Image - Image or Icon
      columnId: Option[
        TableDirect
      ], //AD_Column_ID - Column - Column in the table
      waitTime: Option[
        Int
      ], //WaitTime - Wait Time - Time in minutes to wait (sleep)
      attributeValue: Option[
        String
      ], //AttributeValue - Attribute Value - Value of the Attribute
      attributeName: Option[
        String
      ], //AttributeName - Attribute Name - Name of the Attribute
      docAction: Option[ListType] =
        Option(
          "CO"
        ), //DocAction - Document Action - The targeted status of the document
      value: String, //Value - Search Key - Search key for the record in the format required - must be unique
      dynPriorityUnit: Option[
        ListType
      ], //DynPriorityUnit - Dynamic Priority Unit - Change of priority when Activity is suspended waiting for user
      dynPriorityChange: Option[
        Number
      ], //DynPriorityChange - Dynamic Priority Change - Change of priority when Activity is suspended waiting for user
      eMailRecipient: Option[
        ListType
      ], //EMailRecipient - EMail Recipient - Recipient of the EMail
      eMail: Option[String], //EMail - EMail Address - Electronic Mail Address
      mailTextId: Option[
        TableDirect
      ], //R_MailText_ID - Mail Template - Text templates for mailings
      validTo: Option[
        DateTime
      ], //ValidTo - Valid to - Valid to including this date (last day)
      isMilestone: Option[YesNo] =
        Option(false), //IsMilestone - Is Milestone -
      isSubcontracting: Option[YesNo] =
        Option(false), //IsSubcontracting - Is Subcontracting -
      unitsCycles: Option[Number] =
        Some(
          0
        ), //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
      movingTime: Option[Int], //MovingTime - Moving Time -
      overlapUnits: Option[
        Int
      ], //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
      bPartnerId: Option[
        Search
      ], //C_BPartner_ID - Business Partner  - Identifies a Business Partner
      queuingTime: Option[
        Int
      ], //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
      resourceId: Option[TableDirect], //S_Resource_ID - Resource - Resource
      setupTime: Option[
        Int
      ], //SetupTime - Setup Time - Setup time before starting Production
      validFrom: Option[
        DateTime
      ], //ValidFrom - Valid from - Valid from including this date (first day)
      `yield`: Option[Int], //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
      viewId: Option[
        TableDirect
      ], //AD_View_ID - View - View allows you to create dynamic views of information from the dictionary application
      browseId: Option[TableDirect], //AD_Browse_ID - Smart Browse -
      uuid: Option[String] //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
  ): WfNode =
    WfNode(
      wfNodeId, //AD_WF_Node_ID - Node - Workflow Node (activity), step or process
      name, //Name - Name - Alphanumeric identifier of the entity
      description, //Description - Description - Optional short description of the record
      workflowId, //AD_Workflow_ID - Workflow - Workflow or combination of tasks
      clientId, //AD_Client_ID - Client - Client/Tenant for this installation.
      orgId, //AD_Org_ID - Organization - Organizational entity within client
      isActive, //IsActive - Active - The record is active in the system
      created, //Created - Created - Date this record was created
      createdBy, //CreatedBy - Created By - User who created this records
      updated, //Updated - Updated - Date this record was updated
      updatedBy, //UpdatedBy - Updated By - User who updated this records
      help, //Help - Comment/Help - Comment or Hint
      action, //Action - Action - Indicates the Action to be performed
      windowId, //AD_Window_ID - Window - Data entry or display window
      refWorkflowId, //Workflow_ID - Workflow - Workflow or tasks
      taskId, //AD_Task_ID - OS Task - Operation System Task
      processId, //AD_Process_ID - Process - Process or Report
      formId, //AD_Form_ID - Special Form - Special Form
      isCentrallyMaintained, //IsCentrallyMaintained - Centrally maintained - Information maintained in System Element table
      yPosition, //YPosition - Y Position - Absolute Y (vertical) position in 1/72 of an inch
      entityType, //EntityType - Entity Type - Dictionary Entity Type; Determines ownership and synchronization
      xPosition, //XPosition - X Position - Absolute X (horizontal) position in 1/72 of an inch
      limit, //Limit - Duration Limit - Maximum Duration in Duration Unit
      startMode, //StartMode - Start Mode - Workflow Activity Start Mode
      wfResponsibleId, //AD_WF_Responsible_ID - Workflow Responsible - Responsible for Workflow Execution
      duration, //Duration - Duration - Normal Duration in Duration Unit
      subflowExecution, //SubflowExecution - Subflow Execution - Mode how the sub-workflow is executed
      cost, //Cost - Cost - Cost information
      waitingTime, //WaitingTime - Waiting Time - Workflow Simulation Waiting time
      workingTime, //WorkingTime - Working Time - Workflow Simulation Execution Time
      wfBlockId, //AD_WF_Block_ID - Workflow Block - Workflow Transaction Execution Block
      priority, //Priority - Priority - Indicates if this request is of a high, medium or low priority.
      finishMode, //FinishMode - Finish Mode - Workflow Activity Finish Mode
      joinElement, //JoinElement - Join Element - Semantics for multiple incoming Transitions
      splitElement, //SplitElement - Split Element - Semantics for multiple outgoing Transitions
      imageId, //AD_Image_ID - Image - Image or Icon
      columnId, //AD_Column_ID - Column - Column in the table
      waitTime, //WaitTime - Wait Time - Time in minutes to wait (sleep)
      attributeValue, //AttributeValue - Attribute Value - Value of the Attribute
      attributeName, //AttributeName - Attribute Name - Name of the Attribute
      docAction, //DocAction - Document Action - The targeted status of the document
      value, //Value - Search Key - Search key for the record in the format required - must be unique
      dynPriorityUnit, //DynPriorityUnit - Dynamic Priority Unit - Change of priority when Activity is suspended waiting for user
      dynPriorityChange, //DynPriorityChange - Dynamic Priority Change - Change of priority when Activity is suspended waiting for user
      eMailRecipient, //EMailRecipient - EMail Recipient - Recipient of the EMail
      eMail, //EMail - EMail Address - Electronic Mail Address
      mailTextId, //R_MailText_ID - Mail Template - Text templates for mailings
      validTo, //ValidTo - Valid to - Valid to including this date (last day)
      isMilestone, //IsMilestone - Is Milestone -
      isSubcontracting, //IsSubcontracting - Is Subcontracting -
      unitsCycles, //UnitsCycles - Units by Cycles - The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.
      movingTime, //MovingTime - Moving Time -
      overlapUnits, //OverlapUnits - Overlap Units - Overlap Units are number of units that must be completed before they are moved the next activity
      bPartnerId, //C_BPartner_ID - Business Partner  - Identifies a Business Partner
      queuingTime, //QueuingTime - Queuing Time - Queue time is the time a job waits at a work center before begin handled.
      resourceId, //S_Resource_ID - Resource - Resource
      setupTime, //SetupTime - Setup Time - Setup time before starting Production
      validFrom, //ValidFrom - Valid from - Valid from including this date (first day)
      `yield`, //Yield - Yield % - The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent
      viewId, //AD_View_ID - View - View allows you to create dynamic views of information from the dictionary application
      browseId, //AD_Browse_ID - Smart Browse -
      uuid
    ) //UUID - Immutable Universally Unique Identifier - Immutable Universally Unique Identifier
}
