package com.grupo08.socialmeli.dto.response;

import com.grupo08.socialmeli.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersDto {
    public int user_id;
    public String user_name;
    public List<FollowDto> followers;

}
