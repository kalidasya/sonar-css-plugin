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
package org.sonar.css.tree.impl.scss;

import com.google.common.collect.Iterators;
import org.sonar.css.tree.impl.TreeImpl;
import org.sonar.plugins.css.api.tree.Tree;
import org.sonar.plugins.css.api.tree.css.CompoundSelectorTree;
import org.sonar.plugins.css.api.tree.css.SyntaxToken;
import org.sonar.plugins.css.api.tree.scss.ScssDirectiveTree;
import org.sonar.plugins.css.api.tree.scss.ScssExtendTree;
import org.sonar.plugins.css.api.tree.scss.ScssOptionalFlagTree;
import org.sonar.plugins.css.api.visitors.DoubleDispatchVisitor;

import javax.annotation.Nullable;
import java.util.Iterator;

public class ScssExtendTreeImpl extends TreeImpl implements ScssExtendTree {

  private final ScssDirectiveTree directive;
  private final CompoundSelectorTree selector;

  @Nullable
  private final ScssOptionalFlagTree optionalFlag;

  @Nullable
  private final SyntaxToken semicolon;

  public ScssExtendTreeImpl(ScssDirectiveTree directive, CompoundSelectorTree selector, @Nullable ScssOptionalFlagTree optionalFlag, @Nullable SyntaxToken semicolon) {
    this.directive = directive;
    this.selector = selector;
    this.optionalFlag = optionalFlag;
    this.semicolon = semicolon;
  }

  @Override
  public Kind getKind() {
    return Kind.SCSS_EXTEND;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.forArray(directive, selector, optionalFlag, semicolon);
  }

  @Override
  public void accept(DoubleDispatchVisitor visitor) {
    visitor.visitScssExtend(this);
  }

  @Override
  public ScssDirectiveTree directive() {
    return directive;
  }

  @Override
  public CompoundSelectorTree selector() {
    return selector;
  }

  @Override
  @Nullable
  public ScssOptionalFlagTree optionalFlag() {
    return optionalFlag;
  }

  @Override
  @Nullable
  public SyntaxToken semicolon() {
    return semicolon;
  }

}
