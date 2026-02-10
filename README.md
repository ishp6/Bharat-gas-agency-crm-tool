# Bharat Gas Agency — CRM Tool 🔥

A console-based **Customer Relationship Management (CRM)** application for a Bharat Gas distributor agency, built entirely in **Java** using core **Object-Oriented Programming** concepts.

## 🎯 Features

- **Customer Management** — Register, view, update, search, and deactivate gas connections
- **Cylinder Booking** — Book refills (14.2 KG / 5 KG / 19 KG), track delivery status
- **Payment Management** — Record payments via Cash, UPI, Card, or Net Banking
- **Complaint Management** — File, track, and resolve customer complaints
- **Reports & Dashboard** — View summary stats and detailed reports

## 🧱 OOP Concepts Demonstrated

| Concept | Where Used |
|---|---|
| **Abstraction** | `Person` abstract class, `CRUDOperations` / `Searchable` / `Reportable` interfaces |
| **Encapsulation** | Private fields with public getters/setters in all models |
| **Inheritance** | `Customer extends Person`, `Employee extends Person` |
| **Polymorphism** | Method overriding (`displayDetails()`, `toString()`) |
| **Composition** | `Booking` has-a `Customer` + `Cylinder`, `Payment` has-a `Booking` |
| **Interfaces** | Generic `CRUDOperations<T>`, `Searchable<T>`, `Reportable` |
| **Enums** | 7 enums with display names (ConnectionType, BookingStatus, etc.) |

## 📂 Project Structure

```
src/com/bharatgas/crm/
├── BharatGasCRM.java            ← Main Application
├── enums/                        ← 7 Enum classes
├── model/                        ← 7 Model classes (Person, Customer, Employee, etc.)
├── service/                      ← 3 Interfaces + 4 Service implementations
└── util/                         ← IDGenerator, InputValidator
```

## 🚀 How to Compile & Run

```bash
# Compile
javac -encoding UTF-8 -d out src/com/bharatgas/crm/enums/*.java src/com/bharatgas/crm/util/*.java src/com/bharatgas/crm/model/*.java src/com/bharatgas/crm/service/*.java src/com/bharatgas/crm/BharatGasCRM.java

# Run
java -cp out com.bharatgas.crm.BharatGasCRM
```

## 📸 Sample Output

```
╔══════════════════════════════════════════════════════════════╗
║       🔥  BHARAT GAS AGENCY — CRM MANAGEMENT SYSTEM  🔥     ║
║       Manage Customers · Bookings · Payments · Complaints    ║
╚══════════════════════════════════════════════════════════════╝

┌──────────────────────────────────────────┐
│            📋 MAIN MENU                  │
├──────────────────────────────────────────┤
│  1. 👤 Customer Management               │
│  2. 🛢️  Cylinder Booking                  │
│  3. 💰 Payment Management                │
│  4. 📝 Complaint Management              │
│  5. 📊 Reports & Dashboard               │
│  0. 🚪 Exit                              │
└──────────────────────────────────────────┘
```

## 🛠️ Tech Stack

- **Language**: Java (JDK 1.8+)
- **Architecture**: Layered (Model → Service → UI)
- **Storage**: In-memory (with sample data pre-loaded)

## 👤 Author

Ishwari Patil
