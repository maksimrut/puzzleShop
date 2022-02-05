package com.rutkouski.puzzleshop.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

/**
 * @author Maksim Rutkouski
 *
 * The FooterTag allows to add
 * copyright information on any jsp
 */
public class FooterTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try{
            JspWriter out = pageContext.getOut();
            String tagText = "<footer><p class=\"footer\">© 2022 Copyright by Maksim Rutkouski</p></footer>";
            out.write(tagText);
        } catch (IOException e) {
            throw new JspTagException(e);
        }
        return SKIP_BODY;
    }

}
