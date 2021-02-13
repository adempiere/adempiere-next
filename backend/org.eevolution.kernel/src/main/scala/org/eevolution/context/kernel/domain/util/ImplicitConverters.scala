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

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util

import scala.collection.immutable.List
import scala.jdk.CollectionConverters.ListHasAsScala
import scala.language.implicitConversions

/**
  * Implicit Converters for legacy types from ADempiere
  */
object ImplicitConverters {

  implicit def DecodeDateTime(ldt: LocalDateTime): Timestamp =
    Timestamp.valueOf(ldt)
  implicit def DecodeTimestamp(ts: Timestamp): LocalDateTime =
    ts.toLocalDateTime
  implicit def DecodeList(list: util.List[_]): List[_] = list.asScala.toList
  implicit def DecodeAnyList(parametersList: List[Any]): List[AnyRef] =
    parametersList.map(p => p.asInstanceOf[AnyRef])

  /* Convert to Scala 3
  given DecodeDateTime : Conversion[LocalDateTime, Timestamp] = Timestamp.valueOf(_)
  given DecodeTimestamp : Conversion[Timestamp , LocalDateTime]  = _.toLocalDateTime
  given DecodeList: Conversion[util.List[_] ,  List[_]] = _.asScala.toList
  given DecodeAnyList : Conversion[List[Any], List[AnyRef]] = _.map(p => p.asInstanceOf[AnyRef])
   */

}
