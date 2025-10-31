<img width="807" height="974" alt="image" src="https://github.com/user-attachments/assets/a242214f-eae1-4083-8cf9-1e5a0e6cdb5d" />
@startuml
skinparam nodesep 40
skinparam ranksep 50
skinparam linetype ortho
skinparam shadowing false

' === КЛАССЫ (компактно) ===

class Library {
  -books: List<Book>
  +addBook(Book)
  +getBooks(): List<Book>
  +toString(): String
}

class Book {
  -id, title, author: String
  -year: int
  -genre, price, currency, isbn: String
  -publisher: Publisher
  -language, translator, format: String
  -awards: List<String>
  -reviews: List<Review>
  --
  +addAward(String)
  +addReview(Review)
  +get*/set*()
  +toString(): String
}

class Publisher {
  -name: String
  -address: Address
  +getName()/setName()
  +getAddress()/setAddress()
  +toString(): String
}

class Address {
  -city, country: String
  +getCity()/setCity()
  +getCountry()/setCountry()
  +toString(): String
}

class Review {
  -user: String
  -rating: int
  -comment: String
  +getUser()/setUser()
  +getRating()/setRating()
  +getComment()/setComment()
  +toString(): String
}

class XmlParser {
  +parserLibrary(String): Library
}

class XmlGenerator {
  +generateXml(Library, String)
}

class XmlValidator {
  +validateLibrary(Library): boolean
}

class Main {
  +main(String[])
}

' === СВЯЗИ (компактные) ===
Library ||--o{ Book
Book ||--o| Publisher
Publisher ||--o| Address
Book ||--o{ Review
Book }o--o{ String : awards

Main ..> XmlParser
Main ..> XmlGenerator
Main ..> XmlValidator
XmlParser ..> Library
XmlGenerator ..> Library
XmlValidator ..> Library

' === РАСПОЛОЖЕНИЕ ===
Library -[hidden]left- Main
Book -[hidden]down- Publisher
Publisher -[hidden]down- Address
Book -[hidden]right- Review

@enduml
