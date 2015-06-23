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

import java.util.Set;

/**
 * A class describing a change in a {@link SelectionModel}. 
 *
 * @param <T> The type of the elements
 */
public final class SelectionEvent<T>
{
    /**
     * The {@link SelectionModel} from which this event originated
     */
    private final SelectionModel<T> selectionModel;
    
    /**
     * The set of elements that have been added
     */
    private final Set<T> addedElements;
    
    /**
     * The set of elements that have been removed
     */
    private final Set<T> removedElements;
    
    /**
     * Creates a new selection event. This will store <i>references</i> to
     * the given objects, which also will be returned by the access 
     * methods. Thus, these should be immutable collections.
     * 
     * @param selectionModel The {@link SelectionModel}
     * @param addedElements The set of elements that have been added
     * @param removedElements The set of elements that have been removed
     */
    SelectionEvent(
        SelectionModel<T> selectionModel, 
        Set<T> addedElements,
        Set<T> removedElements)
    {
        this.selectionModel = selectionModel;
        this.addedElements = addedElements;
        this.removedElements = removedElements;
    }
    
    /**
     * Returns the {@link SelectionModel} from which this event originated
     * 
     * @return The {@link SelectionModel} from which this event originated
     */
    public SelectionModel<T> getSelectionModel()
    {
        return selectionModel;
    }
    
    /**
     * Returns the (unmodifiable, possibly empty) set of elements that 
     * have been added. 
     * 
     * @return The set of elements that have been added
     */
    public Set<T> getAddedElements()
    {
        return addedElements;
    }
    
    /**
     * Returns the (unmodifiable, possibly empty) set of elements that 
     * have been removed. 
     * 
     * @return The set of elements that have been removed
     */
    public Set<T> getRemovedElements()
    {
        return removedElements;
    }
    
    @Override
    public String toString()
    {
        return "SelectionEvent[" + 
            addedElements.size() + " added, " +
            removedElements.size() + " removed]";
    }
   
}