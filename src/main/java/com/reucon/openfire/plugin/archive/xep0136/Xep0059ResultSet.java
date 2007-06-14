package com.reucon.openfire.plugin.archive.xep0136;

import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.DocumentFactory;

/**
 * A XEP-0059 result set.
 */
public class Xep0059ResultSet
{
    private String before;
    private String after;
    private int max = -1;
    private int index = -1;
    private String first;
    private int firstIndex = -1;
    private String last;
    private int count = -1;

    public Xep0059ResultSet(Element setElement)
    {
        this.before = setElement.attributeValue("before");
        this.after = setElement.attributeValue("after");
        if (setElement.attribute("max") != null)
        {
            try
            {
                this.max = Integer.parseInt(setElement.attributeValue("max"));
            }
            catch(Exception e)
            {
                // swallow
            }
        }
        if (setElement.attribute("index") != null)
        {
            try
            {
                this.max = Integer.parseInt(setElement.attributeValue("index"));
            }
            catch(Exception e)
            {
                // swallow
            }
        }
    }

    public String getBefore()
    {
        return before;
    }

    public String getAfter()
    {
        return after;
    }

    public int getMax()
    {
        return max;
    }

    public int getIndex()
    {
        return index;
    }

    public void setFirst(String first)
    {
        this.first = first;
    }

    public void setFirstIndex(int firstIndex)
    {
        this.firstIndex = firstIndex;
    }

    public void setLast(String last)
    {
        this.last = last;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public Element buildResponseElement()
    {
        final Element set;

        set = DocumentFactory.getInstance().createElement("set", "http://jabber.org/protocol/rsm");
        if (first != null || firstIndex != -1)
        {
            final Element firstElement = set.addElement("first");
            if (first != null)
            {
                firstElement.setText(first);
            }
            if (firstIndex != -1)
            {
                firstElement.addAttribute("index", Integer.toString(firstIndex));
            }
        }
        if (last != null)
        {
            set.addElement("last").setText(last);
        }
        if (count != -1)
        {
            set.addElement("count").setText(Integer.toString(count));
        }

        return set;
    }
}
