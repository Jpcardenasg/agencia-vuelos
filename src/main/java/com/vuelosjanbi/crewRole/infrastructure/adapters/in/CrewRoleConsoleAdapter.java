package com.vuelosjanbi.crewRole.infrastructure.adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Scanner;

import com.vuelosjanbi.crewRole.application.CrewRoleService;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;

@Controller
public class CrewRoleConsoleAdapter {

  @Autowired
  private CrewRoleService crewRoleService;

  public void start() {

    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("1. Create Crew Role.");
      System.out.println("2. Update Crew Role.");
      System.out.println("3. Delete Crew Role.");
      System.out.println("4. List all Crew Roles.");
      System.out.println("5. Exit.");
      switch (scanner.nextInt()) {
        case 1:
          System.out.println("Type the name of the crew role:");
          String crewRoleName = scanner.nextLine();

          crewRoleService.createCrewRole(new CrewRole(crewRoleName));
          break;
        case 2:
          System.out.println("Choose the crew role you want to modify:");
          crewRoleService.getAllCrewRoles().forEach(crewRole -> {
            System.out.printf("ID: %d =, Name: %s\n", crewRole.getId(), crewRole.getName());
          });
          Long updateCrewRoleId = scanner.nextLong();
          scanner.nextLine();
          System.out.println("Type the new name of the crew role:");
          String newCrewRoleName = scanner.nextLine();
          CrewRole updatedCrewRole = new CrewRole(updateCrewRoleId, newCrewRoleName);
          crewRoleService.updateCrewRole(updatedCrewRole);
          break;
        case 3:
          System.out.println("Choose the crew role you want to delete:");
          crewRoleService.getAllCrewRoles().forEach(crewRole -> {
            System.out.printf("ID: %d =, Name: %s\n", crewRole.getId(), crewRole.getName());
          });
          Long deleteCrewRoleId = scanner.nextLong();
          scanner.nextLine();
          crewRoleService.deleteCrewRole(deleteCrewRoleId);
          break;
        case 4:
          crewRoleService.getAllCrewRoles().forEach(crewRole -> {
            System.out.printf("ID: %d =, Name: %s\n", crewRole.getId(), crewRole.getName());
          });
          break;
        case 5:
          return;
        default:
          System.out.println("Invalid choice. Please try again.");
          break;
      }
    }
  }
}
