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

                      * * *

When we talk about all the collection-like entities like
*Book* *Chapter*, they are only meaningful within a single
*Book* scope. If there is any cross *Book*
reference, a new entity must be introduced *Alias*.   

*Alias* is a reference linking to external resource, in this context,
means another *Book*. However, to make this simple, *Alias* will only
represent collections. entities like *Article* *Preface* can be directly used in
another *Book* scope. 

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

