# Package Sorter

Function : sort(width, height, length, weight)
A simple Java Function that sorts packages into categories based on their size and weight.

Requires
- Java > 11+
- Maven

Packages are sorted into three categories:
- **STANDARD** - Small, light packages
- **SPECIAL** - Packages that are either bulky OR heavy
- **REJECTED** - Packages that are both bulky AND heavy

A package is considered:
- **Bulky** if any dimension ≥ 150cm or volume ≥ 1,000,000 cm³
- **Heavy** if weight ≥ 20kg

How to Use:
    PackageSorter sorter = new PackageSorter();
    String result = sorter.sort(width, height, length, weight);
    Returns: "STANDARD", "SPECIAL", or "REJECTED"


To Run:
mvn clean test    
mvn compile  
java -cp sorterutil/target/classes com.thoughtful.Main


