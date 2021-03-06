/*
 * SonarQube CSS / SCSS / Less Analyzer
 * Copyright (C) 2013-2016 Tamas Kende and David RACODON
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.css.checks.common;

import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.css.checks.Tags;
import org.sonar.css.model.property.standard.TextTransform;
import org.sonar.plugins.css.api.tree.Tree;
import org.sonar.plugins.css.api.tree.css.IdentifierTree;
import org.sonar.plugins.css.api.tree.css.PropertyDeclarationTree;
import org.sonar.plugins.css.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

import java.util.List;

@Rule(
  key = "text-transform-uppercase",
  name = "\"text-transform\" properties should not be set to \"uppercase\" or \"capitalize\" for some languages",
  priority = Priority.MAJOR,
  tags = {Tags.BROWSER_COMPATIBILITY})
@SqaleConstantRemediation("5min")
public class TextTransformUppercaseCheck extends DoubleDispatchVisitorCheck {

  private static final List<String> FORBIDDEN_VALUES = ImmutableList.of("uppercase", "capitalize");

  @Override
  public void visitPropertyDeclaration(PropertyDeclarationTree tree) {
    if (tree.property().standardProperty() instanceof TextTransform
      && !tree.value().sanitizedValueElements().isEmpty()
      && tree.value().sanitizedValueElements().get(0).is(Tree.Kind.IDENTIFIER)
      && FORBIDDEN_VALUES.contains(((IdentifierTree) tree.value().sanitizedValueElements().get(0)).text().toLowerCase())) {
      addPreciseIssue(tree, "Remove this \"text-transform\" property declaration.");
    }
    super.visitPropertyDeclaration(tree);
  }

}
