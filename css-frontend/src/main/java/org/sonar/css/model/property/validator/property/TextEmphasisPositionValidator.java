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
package org.sonar.css.model.property.validator.property;

import java.util.List;

import org.sonar.css.model.property.validator.ValueElementValidator;
import org.sonar.css.model.property.validator.ValueValidator;
import org.sonar.css.model.property.validator.valueelement.IdentifierValidator;
import org.sonar.plugins.css.api.tree.Tree;
import org.sonar.plugins.css.api.tree.css.ValueTree;

public class TextEmphasisPositionValidator implements ValueValidator {

  private static final ValueElementValidator OVER_UNDER_VALIDATOR = new IdentifierValidator("over", "under");
  private static final ValueElementValidator RIGHT_LEFT_VALIDATOR = new IdentifierValidator("right", "left");

  @Override
  public boolean isValid(ValueTree valueTree) {
    List<Tree> valueElements = valueTree.sanitizedValueElements();
    if (valueElements.size() != 2) {
      return false;
    } else {
      return OVER_UNDER_VALIDATOR.isValid(valueElements.get(0)) && RIGHT_LEFT_VALIDATOR.isValid(valueElements.get(1))
        || RIGHT_LEFT_VALIDATOR.isValid(valueElements.get(0)) && OVER_UNDER_VALIDATOR.isValid(valueElements.get(1));
    }
  }

  @Override
  public String getValidatorFormat() {
    return "[ over | under ] && [ right | left ]";
  }

}
