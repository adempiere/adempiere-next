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
  * Created by victor.perez@e-evolution.com , www.e-evolution.com.
  * Modified by emeris.hernandez@e-evolution.com , www.e-evolution.com on 19/10/17.
  */

package org.eevolution.context.dictionary.domain.repository

import org.eevolution.context.dictionary.domain.model.{Attribute, Entity}
import org.eevolution.context.kernel.domain.ubiquitouslanguage.{Id, TableDirect}
import zio.RIO

trait attributeRepositoryApiBase[-R] {
  def getById(id: Id): RIO[R, Option[Attribute]]

  def getByEntity(entity: Entity): RIO[R, List[Attribute]]

  def getAll: RIO[R, List[Attribute]]

  def getAllByClientId(tenantId: TableDirect): RIO[R, List[Attribute]]

  def save(attribute: Attribute): RIO[R, Attribute]
}
