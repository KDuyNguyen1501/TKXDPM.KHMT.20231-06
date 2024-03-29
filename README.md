# TKXDPM.VN.20231-06

Template for managing Capstone's project in the Software Design and Construction course in the 2023.1 semester.

## Table of contents

- [TKXDPM.VN.20231-06](#tkxdpmvn20231-06)
  - [Table of contents](#table-of-contents)
  - [Quick start](#quick-start)
  - [What's included](#whats-included)
  - [Report Content](#report-content)
  - [Pull request template](#pull-request-template)

## Quick start

Before using this Github repository, everyone needs to register their group information using the table below:

| Name              | Role        |
| :---------------- | :---------- |
| Nguyễn Khánh Duy  | Team Leader |
| Nguyễn Bá Duy     | Member      |
| Nguyễn Đức Dũng   | Member      |
| Nguyễn Tùng Dương | Member      |

## What's included

The recommended structure is as follows:

- `AIMS`: folder containing the team's AIMS base code
- `assets`: folder containing images that you want to include in the report file
- `README.md`: weekly report file, individuals are required to update their tasks and how their do it into this file
- `Template.md`: template for the weekly report
- `pull_request_template.md`: pull request's description template

## Report Content

The recommended report structure will have a format as follows:

<details>
  <summary>W09: 27/11/2023~03/12/2023 </summary>
<br>
<details>
<summary>Nguyễn Khánh Duy</summary>
<br>

- Assigned tasks:

  - Comment level coupling cho controller folder và entity.cart folder

- Implementation details:
  - Pull Request(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/1]()
  - Specific implementation details:
    - comment level coupling của các class trong thư mục controller và thư mục entity.cart

</details>

<details>
<summary>Nguyễn Bá Duy</summary>
<br>

- Assigned tasks:

  - Comment level coupling cho entity

- Implementation details:
  - Pull Request(s): https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/4
  - Specific implementation details:
    - Describe specific in detail what you did last week
    - You can attach images if you want

</details>

<details>
<summary>Nguyễn Đức Dũng</summary>
<br>

- Assigned tasks:

  - Comment level coupling cho các class handler trong folder views

- Implementation details:
  - Pull Request(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/5]()
  - Specific implementation details:
    - Describe specific in detail what you did last week
    - You can attach images if you want

</details>

<details>
<summary>Nguyễn Tùng Dương</summary>
<br>

- Assigned tasks:

  - Comment level coupling cho ViewCartController với 3 controller còn lại

- Implementation details:
  - Pull Request(s): []()
  - Specific implementation details:
    - Comment level cohesion cho các hàm trong folder subsystem và utils

</details>

</details>

---

<details>
  <summary>W10: 04/12/2023~10/12/2023 </summary>
<br>
<details>
<summary>Nguyễn Khánh Duy</summary>
<br>

- Assigned tasks:

  - Comment level cohesion cho controller folder và entity.cart folder

- Implementation details:
  - Pull Request(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/7]()
  - Specific implementation details:
    - comment level cohesion của các class trong thư mục controller và thư mục entity.cart

</details>

<details>
<summary>Nguyễn Tùng Dương</summary>
<br>

- Assigned tasks:

  - Comment level cohesion cho folder subsystem và utils

- Implementation details:
  - Pull Request(s): [Attach links to your pull requests here. You can attach multiple pull requests]()
  - Specific implementation details:
    - Comment level cohesion cho các hàm trong folder subsystem và utils

</details>

</details>

<details>
  <summary>W11: 11/12/2023~17/12/2023 </summary>
<br>
<details>
<summary>Nguyễn Đức Dũng</summary>
<br>

- Assigned tasks:

  - cmt các nguyên lý solid bị vi phạm (sửa đổi code néu có) cho các class handler trong folder views

- Implementation details:
  - Pull Request(s): []()
  - Commit(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/commit/808571fe4d7419035896c9e0d389c1e3bbc2d43b]()
  - Specific implementation details:
    - cmt các nguyên lý solid bị vi phạm (sửa đổi code néu có) cho các class handler trong folder views

</details>

<details>
<summary>Nguyễn Khánh Duy</summary>
<br>

- Assigned tasks:

  - Xác định các lớp vi phạm SOLID principles của các lớp trong thư mục controller và entity/cart

- Implementation details:
  - Pull Request(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/8]()
  - Specific implementation details:
    - Xác định và comment vi phạm về SOLID principles của các lớp trong thư mục controller và entity/cart

</details>

</details>

<details>
<summary>Nguyễn Bá Duy</summary>
<br>

- Assigned tasks:

  - Xác định các lớp vi phạm SOLID principles của các lớp trong thư mục entity

- Implementation details:
  - Pull Request(s): [https://github.com/KDuyNguyen1501/TKXDPM.KHMT.20231-06/pull/9]()
  - Specific implementation details:
    - Xác định và comment vi phạm về SOLID principles của các lớp trong thư mục entity

</details>

</details>

## Pull request template

- You are required to create a pull request according to these steps:
  - Use the `pull_request_template.md` file when writing the description section in the pull request
  - The `title` of the pull request should follow the format below:
    - `Feature/Topic/Hotfix/Fix: Task Name`. Example: `Feature: Build View Controller`
    - Explain:
      - `Feature`: When the branch has the task of changing the main code of the project
      - `Topic`: When the branch only has the task of research, without directly changing the main code of the project
      - `Hotfix`: When you discover code on the production environment
      - `Fix`: When you discover a bug in a branch that has not been merged into the production environment
  - After creating the pull request, decide who will merge the code within your team.
  - You don't need to follow the Wxx format as I mentioned before
  - Each person will have multiple pull requests
  - Whoever makes the pull request, attach that pull request to the report you attached above. Section `pull request(s)`
