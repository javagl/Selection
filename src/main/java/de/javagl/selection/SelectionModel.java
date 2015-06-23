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
import java.util.Set;

/**
 * Interface describing a selection model
 *
 * @param <T> The type of the elements
 */
public interface SelectionModel<T>
{
    /**
     * Adds the given element to this selection
     * 
     * @param element The element to add
     */
    void addToSelection(T element);

    /**
     * Removes the given element from this selection
     * 
     * @param element The element to remove
     */
    void removeFromSelection(T element);

    /**
     * Adds the given elements to this selection
     * 
     * @param elements The elements to add
     */
    void addToSelection(Collection<? extends T> elements);

    /**
     * Remove the given elements from this selection
     * 
     * @param elements The elements to remove
     */
    void removeFromSelection(Collection<? extends T> elements);
    
    /**
     * Sets the given elements to be the selection of this model. <br>
     * <br>
     * This will cause a single {@link SelectionEvent} to be fired. The
     * {@link SelectionEvent#getRemovedElements() removed elements}
     * and {@link SelectionEvent#getAddedElements() added elements} 
     * of this event will precisely describe the <i>state change</i>:
     * When the initial selection is <code>{0,1,2,3}</code>, and 
     * this method is called with the set <code>{2,3,4,5}</code>,
     * then the {@link SelectionEvent#getRemovedElements() removed elements}
     * of the resulting event will be <code>{0,1}</code>, and the
     * {@link SelectionEvent#getAddedElements() added elements} will
     * be <code>{4,5}</code>.
     * 
     * @param elements The elements to select
     */
    void setSelection(Collection<? extends T> elements);
    
    /**
     * Removes all elements from this selection 
     */
    void clear();
    
    /**
     * Returns whether the given element is currently selected
     * 
     * @param element The element
     * @return Whether the element is selected
     */
    boolean isSelected(T element);
    
    /**
     * Returns an unmodifiable view on the set of currently selected
     * elements
     * 
     * @return The currently selected elements
     */
    Set<T> getSelection();
    
    /**
     * Add the given {@link SelectionListener} to be informed about changes
     * in this selection model
     * 
     * @param selectionListener The {@link SelectionListener} to add
     */
    void addSelectionListener(SelectionListener<T> selectionListener);

    /**
     * Remove the given {@link SelectionListener} 
     * 
     * @param selectionListener The {@link SelectionListener} to remove
     */
    void removeSelectionListener(SelectionListener<T> selectionListener);
}
