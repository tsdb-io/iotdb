/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.queryengine.transformation.dag.column.unary.scalar;

import org.apache.iotdb.db.queryengine.transformation.dag.column.ColumnTransformer;
import org.apache.iotdb.db.queryengine.transformation.dag.column.unary.UnaryColumnTransformer;

import org.apache.tsfile.block.column.Column;
import org.apache.tsfile.block.column.ColumnBuilder;
import org.apache.tsfile.enums.TSDataType;
import org.apache.tsfile.read.common.type.Type;

public class Log10ColumnTransformer extends UnaryColumnTransformer {
  public Log10ColumnTransformer(Type returnType, ColumnTransformer childColumnTransformer) {
    super(returnType, childColumnTransformer);
  }

  @Override
  protected void doTransform(Column column, ColumnBuilder columnBuilder) {
    for (int i = 0, n = column.getPositionCount(); i < n; i++) {
      if (!column.isNull(i)) {
        if (TSDataType.DOUBLE.equals(column.getDataType())) {
          columnBuilder.writeDouble(Math.log10(column.getDouble(i)));
        } else if (TSDataType.FLOAT.equals(column.getDataType())) {
          columnBuilder.writeDouble(Math.log10(column.getFloat(i)));
        } else if (TSDataType.INT32.equals(column.getDataType())) {
          columnBuilder.writeDouble(Math.log10(column.getInt(i)));
        } else if (TSDataType.INT64.equals(column.getDataType())) {
          columnBuilder.writeDouble(Math.log10((double) column.getLong(i)));
        }
      } else {
        columnBuilder.appendNull();
      }
    }
  }
}
