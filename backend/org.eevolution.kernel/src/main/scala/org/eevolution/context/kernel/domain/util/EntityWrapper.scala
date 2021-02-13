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
  **/

package org.eevolution.context.kernel.domain.util

import org.compiere.model.{MTable, PO}
import org.compiere.util.Env
import org.eevolution.context.kernel.domain.model.{DomainModel, Identifiable}

class EntityWrapper {

  def getEntity[T](entity: T, properties: Seq[(String, String)]): PO = {
    val keyValues = getKeyValues(entity.asInstanceOf[DomainModel])
    val entityPO: PO =
      MTable.get(Env.getCtx, entity.asInstanceOf[Identifiable].entityName)
    properties.foreach {
      case (property, column) =>
        val value: Any = keyValues.get(property)
        entityPO.set_ValueOfColumn(column, value)
    }
    entityPO
  }

  private def getKeyValues[T <: DomainModel](entity: T): Map[String, Any] = {
    val keyValues =
      (entity.productElementNames zip entity.productIterator).toMap
    keyValues
  }

  def getEntityUpdated[T](
      entityPO: PO,
      entity: T,
      properties: Seq[(String, String)]
  ): T = {
    properties.foreach {
      case (property, column) =>
        val value: AnyRef = entityPO.get_Value(column)
        entity.getClass.getDeclaredFields.find(_.getName == property) match {
          case Some(field) =>
            field.setAccessible(true)
            field.set(entity, value)
          case None =>
            throw new IllegalArgumentException("No field named " + property)
        }
    }
    entity
  }

  def save[T](entity: T, properties: Seq[(String, String)]): T = {
    val contractPO = EntityWrapper.getEntity(entity, properties)
    contractPO.saveEx()
    val contractUpdated =
      EntityWrapper.getEntityUpdated(contractPO, entity, properties)
    contractUpdated
  }
}

object EntityWrapper extends EntityWrapper
