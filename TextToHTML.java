/*
 * @(#)TextToHTML.java
 *
 * Copyright (C) 2002-2003 by Erik C. Thauvin (erik@thauvin.net)
 * All rights reserved.
 *
 * Donated to the Roller Weblogger project for publication under
 * the terms of the Roller Software License.
 */

//package com.imacination.jtalk; 

/**
 * Converts/encodes text to HTML or XML.
 *
 * @author Erik C. Thauvin
 *
 * @created October 31, 2002
 */
public class TextToHTML
{
	/**
	 * The main program usage.
	 */
	private static final String USAGE = "Usage: java " + TextToHTML.class.getName() + " [-xml] <text-to-convert>";

	/**
	 * Converts a character to HTML or XML entity.
	 *
	 * @param ch The character to convert.
	 * @param xml Convert the character to XML if set to true.
	 *
	 * @return The converted string.
	 */
	public static final String charToHTML(char ch, boolean xml)
	{
		int c;

		// Convert left bracket
		if (ch == '<')
		{
			return ("&lt;");
		}

		// Convert left bracket
		else if (ch == '>')
		{
			return ("&gt;");
		}

		// Convert ampersand
		else if (ch == '&')
		{
			return ("&amp;");
		}

		// High-ASCII character
		else if (ch >= 128)
		{
			c = ch;

			return ("&#" + c + ';');
		}

		// Convert double quote
		else if (xml && (ch == '"'))
		{
			return ("&quot;");
		}

		// Convert single quote
		else if (xml && (ch == '\''))
		{
			return ("&#39;");
		}

		// No conversion
		else
		{
			// Return character as string
			return (String.valueOf(ch));
		}
	}

	/**
	 * The main program for the TextToHtml class.
	 *
	 * @param args The command line arguments.
	 */
	public static final void main(String[] args)
	{
		if (args.length > 0)
		{
			boolean xml = false;
			int i = 0;

			if (args[0].startsWith("-x"))
			{
				if (args.length == 1)
				{
					usage();
				}

				xml = true;
				i++;
			}

			final StringBuffer cmdline = new StringBuffer();

			for (; i < args.length; i++)
			{
				if (cmdline.length() != 0)
				{
					cmdline.append(' ');
				}

				cmdline.append(args[i]);
			}

			System.out.println(textToHTML(cmdline.toString(), xml));
		}
		else
		{
			usage();
		}
	}

	/**
	 * Converts a text string to HTML or XML entities.
	 *
	 * @param text The string to convert.
	 * @param xml Convert the string to XML if set to true.
	 *
	 * @return The converted string.
	 */
	public static final String textToHTML(String text, boolean xml)
	{
		final StringBuffer html = new StringBuffer();

		// Loop thru each characters of the text
		for (int i = 0; i < text.length(); i++)
		{
			// Convert character to HTML/XML
			html.append(charToHTML(text.charAt(i), xml));
		}

		// Return HTML/XML string
		return html.toString();
	}

	/**
	 * Converts a text string to HTML or XML entities.
	 *
	 * @param text The string to convert.
	 *
	 * @return The converted string.
	 */
	public static final String textToHTML(String text)
	{
		return textToHTML(text, false);
	}

	/**
	 * Converts a text string to XML entities.
	 *
	 * @param text The string to convert.
	 *
	 * @return The converted string.
	 */
	public static final String textToXML(String text)
	{
		return textToHTML(text, true);
	}

	/**
	 * Prints the usage and exits.
	 */
	private static final void usage()
	{
		System.err.println(USAGE);
		System.exit(1);
	}
}
