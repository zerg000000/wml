WML Specification
=================

Introduction
------------

This is a piracy document management system. With piracy in mind,
providing easy-to-use document writing and organizing functionalities.

Technology
----------

The following would be the core technology that affects user.

- Markdown

The following would be the core technology that implements the system.

- Bootstrap
- Compojure
- MongoDB

Requirement
-----------

### Conceptual Model

Since document or book have uncountable different formats, we cannot
model all those features as Letax did. We try to keep the model simple
and good enough for writing novel or technical document. However,
even in this context, that would be too complex for my standard.
Therefore, I define a model here to present a very simple
Document Concept Model to suit my need.

* * *

A complete *Book*, *Episode*, *Section*, *Chapter* actually
is a collection of articles or others collections.
they have no different at least on usecase.

So, *Book* and *Chapter* are chosen to represent collections in this context.

*Book* is a collection of *Chapter* and articles.
*Book* itself represents a conceptual complete collection
of a topic, which is different from physical book.
For Example, A Series of books in book store will be considered
as a *Book* in this context. A book inside a series will be
considered as a *Chapter* in this context.

*Chapter* is a collection of *Chapter* and articles. One 
Important thing is the *Chapter* does not allow any circular reference.
Which means *Chapter* cannot contains a *Chapter* that contains itself.

#### Case 1( direct circular reference)

Chapter ABC
* Chapter 123
* Chapter 234
* Article qwe
* Chapter ABC <--- wrong! circular reference!

#### Case 2( indirect circular reference)

Chapter ABC
* Chapter 123 <--- wrong! circular reference!
* Article G

Chapter 123
* Chapter ABC <--- wrong! circular reference!
* Article E

*Book* and *Chapter* is the default and convention term in
describe the system, but will not be restrictly enforce 
when user using the system. Users can rename any seperate *Book*,
*Chapter* to suit for their needs.

To summary, *Book* and *Chapter* are collection of *Chapter* and articles.
*Book* is the top level entity to contain all the others.
*Chapter* is a collection that suit for any purpose,
but cannot be the top level entity. Both *Book* and
*Chapter* can be renamed as user needs.

* * *

*Article* is the minimum parts of the system. *Article cannot be
subdivide to any smaller parts that will be describe as an
single entity.

*Article* is some text that could exists seperately without
losing its meaning. In other word, a minimum set of text that
form a complete meaning for a *Chapter* or a *Book*.
e.g. a preface of a physical book.


*Preface*, *Reference*, *Glossory*, *TOC* are just a specialized *Article*.
*TOC*, *Annotation* should be generated automatically.

### Workflow

Functional Requirement
----------------------

### Piracy

Since the piracy policy of different countries becomes less and less
restrictive, the system should not make any assumption on
country's piracy policy.

#### How to

1. readable data will only available on client-side at runtime, when requested.
2. must have no way to recover data from single-side( server or client).
3. data stored on server should be theoretical in-crackable. 
4. channel must be always private.

#### Problems

1. not way to recover when "disasters" happen.
2. assume a "complete safe" client.
3. not additional processing can be preformed.

### Publishing


Technical Requirement
---------------------

At *Article* level, everything can be referenced using uri.
Everything inside an *Article* should be referenced using relative uri.
Everything inside the system should be referenced using absolute uri
without base domain.

### Entities

*Book*

    {
      "title" : "The programmer of first order",
      "type" : "Series",
      "categories" : ["novel", "magic", "dnd"]
      "authors" : ["albert.lai"],
      "pub_date" : 129830293,
      "collections" : [
        { 
	  "title" : "Preface",
	  "href" : "/albert.lai/books/1/articles/1"
	},
	{
	  "title" : "Game of Hungers",
	  "subtitle" : "The price of fool",
          "href" : "/albert.lai/books/1/chapters/1"
	}
      ],
      "version" : 1,
      "create_date" : 123422323,
      "create_by" : "albert.lai"
    }

*Chapter*

*Chapter* depth <= 10

    {
      "title" : "Game of Hungers",
      "subtitle" : "The price of fool",
      "type" : "Chapter",
      "collections" : [
        {
	  "title" : "The new king of Forena",
	  "href" : "/albert.lai/books/1/articles/2"
	},
	{
	  "title" : "Training of a king",
	  "href" : "/albert.lai/books/1/chapters/2"
	}
      ],
      "version" : 1,
      "create_date" : 12321312312,
      "
    }

*Article*

    {
      "title" : "The new king of Forena",
      "style" : "/albert.lai/books/1/css/art1.css",
      "format" : "markdown",
      "body" : "Once upon a time....",
      "version" : 1,
      "create_date" : 1234243234
    }

### API

* GET - read
* POST - partial change, or new resource under root entity
* PUT - update
* DELETE - delete

#### Author API

    /{author} - reserved
    GET, POST, PUT /{author}/profile - author information
    GET, POST /{author}/books - list of all books written by this author
    GET, POST, PUT, DELETE /{author}/books/{id|name} - show the book content, not flatten
    GET /{author}/books/{id|name}/flatten.{format} - flatten the book as a single document
    GET, POST /{author}/books/{id|name}/chapters - list of chapters in the book, not flatten
    GET, POST, PUT, DELETE /{author}/books/{id|name}/chapters/{id|name} - show the chapter content
    GET, POST /{author}/books/{id|name}/articles - list of articles in the book, not flatten
    GET, POST, PUT /{author}/books/{id|name}/articles/{id|name} -  show the article content(plain text)

#### Publishing API

    POST/showrooms/publish - for publishing book to /showrooms
    GET /showrooms/{name} - list of books in showrooms
    GET /showrooms/{name}/books/{id|name} - the actual book for read anywhere.
    
#### Public Shelf API

    GET, POST /shelfs - create new shelf
    GET /shelfs/{shelf} - shelf content

