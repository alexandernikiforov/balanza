# Project: Balanza

## Overview

**Balanza** is a mobile-first web application for tracking daily food intake, calories, and nutritional balance. It supports barcode scanning (via Open Food Facts), personal product management, and reusable meal templates. The goal is to provide a clean, ad-free, open-source alternative to bloated commercial calorie counters.

- **Frontend**: Angular
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL (or H2 for development)
- **CI/CD**: GitHub Actions
- **License**: AGPL-3.0
- **Hosting**: TBD (possible Azure or Render)
- **PWA support**: Yes, including mobile home screen install and offline access

---

## Key Concepts

### DailyIntake
Represents all food consumed by a user on a specific day.

- One per user per day
- Contains a list of `FoodItem`
- Does not group by meal type (e.g., breakfast/lunch/dinner)

### FoodItem
A single entry representing a consumed portion of food.

- Attributes:
  - `foodName: string`
  - `weightGrams: float`
  - `calories: float`
  - `proteinGrams: float`
  - `fatGrams: float`
  - `carbsGrams: float`
  - `productId: optional` – reference to `Product`
  - `createdAt: datetime`

### Product
A source of nutritional information per 100g.

- May come from:
  - Open Food Facts (`scope = "global"`)
  - User-defined (`scope = "user"`)
- Attributes:
  - `name: string`
  - `barcode: optional string`
  - `caloriesPer100g`, `proteinPer100g`, `fatPer100g`, `carbsPer100g`
  - `scope: "global" | "user"`
  - `userId: optional` (if `scope = "user"`)

### Template
A reusable set of food entries.

- Not tied to a date
- Can be used to quickly populate a `DailyIntake`
- Attributes:
  - `name: string`
  - `userId: UUID`
  - Contains `TemplateItem[]` (same shape as `FoodItem`, no `createdAt`)

---

## Functional Requirements

- Add/edit/delete `FoodItem` in `DailyIntake`
- Add multiple `FoodItem` entries at once
- Calculate total calories/protein/fat/carbs per day
- Create `Template` from a group of `FoodItem`
- Copy template to `DailyIntake` (handled in UI)
- Barcode scanning via Open Food Facts API
- Search for products by name or barcode
- Add and manage user-defined products
- Save and reuse frequently consumed food combinations
- Responsive mobile-first layout
- Optional PWA install and offline support

---

## Non-Functional Requirements

- Open-source with AGPL-3.0 license
- Minimalistic, clean UI (no ads, no trackers)
- CI/CD with GitHub Actions for build and test
- Fast initial load and lightweight performance
- Ready for localization (i18n planned)

---

## Terminology for AI Agents

| Concept       | Model Name   | Notes |
|---------------|--------------|-------|
| Daily food log | `DailyIntake` | One per user per day |
| Consumed food item | `FoodItem` | Concrete entry with weight and nutrients |
| Product data | `Product` | Source of nutritional values (100g basis) |
| Reusable meal | `Template` | A group of food items |
| Reused item in template | `TemplateItem` | Same as `FoodItem`, without date |
| Meal types | ❌ Not used | No grouping into breakfast/lunch/dinner |

- Avoid naming `FoodItem` as "portion" in code
- Use `Product` for any nutrient source
- Barcode scanning should try user-defined products before Open Food Facts
- `getSummary(date)` is not needed — aggregate in UI from `FoodItem[]`

---

## AI Assistant Guidelines

- Follow established model and naming conventions
- Prefer single-responsibility service interfaces:
  - `DailyIntakeService`
  - `ProductService`
  - `TemplateService`
- Don't implement advanced analytics or gamification
- Keep UIs minimal and focused on usability
- Always preserve offline/PWA compatibility

---

## Author Notes

This project is created for personal use by the author and their family/friends. While it is open-source, its values are simplicity, privacy, and usability. If you’re an AI agent assisting with development, please respect the design goals and architectural choices defined in this document.
