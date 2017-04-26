/**
 * Copyright © 2016-2017 Cloudera, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cloudera.labs.envelope.plan;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Test;

import com.cloudera.labs.envelope.spark.Contexts;

import scala.Tuple2;

public class TestOverwritePlanner {

  @Test
  public void testPlanner() {
    Dataset<Row> testData = Contexts.getSparkSession().sql("SELECT 'test'");

    BulkPlanner planner = new OverwritePlanner();
    List<Tuple2<MutationType, Dataset<Row>>> plan = planner.planMutationsForSet(testData);

    assertEquals(plan.size(), 1);
    assertEquals(plan.get(0)._1(), MutationType.OVERWRITE);
    assertEquals(plan.get(0)._2(), testData);
  }

}
