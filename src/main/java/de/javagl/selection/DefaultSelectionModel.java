/*
 * www.javagl.de - Selection - A generic selection model
 *
 * Copyright (c) 2013-2015 Marco Hutter - http://www.javagl.de
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package de.javagl.selection;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Default implementation of a {@link SelectionModel}
 *
 * @param <T> The type of the elements in this selection
 */
class DefaultSelectionModel<T> extends AbstractSelectionModel<T>
{
    /**
     * The selection
     */
    private final Set<T> selection;
    
    /**
     * The unmodifiable view on the selection
     */
    private final Set<T> selectionView;
    
    /**
     * Default constructor
     */
    DefaultSelectionModel()
    {
        this.selection = new LinkedHashSet<T>();
        this.selectionView = Collections.unmodifiableSet(selection);
    }

    @Override
    public void addToSelection(T element)
    {
        boolean changed = selection.add(element);
        if (changed)
        {
            fireSelectionChanged(element, null);
        }
    }

    @Override
    public void removeFromSelection(T element)
    {
        boolean changed = selection.remove(element);
        if (changed)
        {
            fireSelectionChanged(null, element);
        }
    }

    @Override
    public void addToSelection(Collection<? extends T> elements)
    {
        Set<T> addedElements = new LinkedHashSet<T>();
        for (T element : elements)
        {
            boolean wasAdded = selection.add(element);
            if (wasAdded)
            {
                addedElements.add(element);
            }
        }
        if (!addedElements.isEmpty())
        {
            fireSelectionChanged(
                Collections.unmodifiableSet(addedElements), null);
        }
    }

    @Override
    public void removeFromSelection(Collection<? extends T> elements)
    {
        Set<T> removedElements = new LinkedHashSet<T>();
        for (T element : elements)
        {
            boolean wasRemoved = selection.remove(element);
            if (wasRemoved)
            {
                removedElements.add(element);
            }
        }
        if (!removedElements.isEmpty())
        {
            fireSelectionChanged(
                null, Collections.unmodifiableSet(removedElements));
        }
    }
    
    @Override
    public void setSelection(Collection<? extends T> elements)
    {
        if (!selection.equals(elements))
        {
            Set<T> removedElements = new LinkedHashSet<T>(selection);
            Set<T> addedElements = new LinkedHashSet<T>(elements);
            removedElements.removeAll(addedElements);
            addedElements.removeAll(selection);
            selection.clear();
            selection.addAll(elements);
            if (!addedElements.isEmpty() || !removedElements.isEmpty())
            {
                fireSelectionChanged(
                    Collections.unmodifiableSet(addedElements), 
                    Collections.unmodifiableSet(removedElements));
            }
        }
    }
    
    @Override
    public void clear()
    {
        if (!selection.isEmpty())
        {
            Set<T> removedElements = new LinkedHashSet<T>(selection);
            selection.clear();
            fireSelectionChanged(null, removedElements);
        }
    }

    @Override
    public boolean isSelected(T element)
    {
        return selection.contains(element);
    }

    @Override
    public Set<T> getSelection()
    {
        return selectionView;
    }
    
}
