package com.example.securitytestforiva.dto.commands.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateGroupCommand {
    private String groupName;
}
