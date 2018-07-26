package com.abel.shiro.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abel.shiro.dao.MemberModelMapper;
import com.abel.shiro.dao.MemberRoleModelMapper;
import com.abel.shiro.dao.PermissionModelMapper;
import com.abel.shiro.dao.RoleModelMapper;
import com.abel.shiro.dao.RolePermModelMapper;
import com.abel.shiro.model.MemberModel;
import com.abel.shiro.model.MemberModelCriteria;
import com.abel.shiro.model.MemberRoleModel;
import com.abel.shiro.model.MemberRoleModelCriteria;
import com.abel.shiro.model.PermissionModel;
import com.abel.shiro.model.PermissionModelCriteria;
import com.abel.shiro.model.RoleModel;
import com.abel.shiro.model.RoleModelCriteria;
import com.abel.shiro.model.RolePermModel;
import com.abel.shiro.model.RolePermModelCriteria;

/**
 * @author Abel.lin
 */
@Service
public class MemberService {
	@Autowired
	private MemberModelMapper memberMapper;
	@Autowired
	private RoleModelMapper roleMapper;
	@Autowired
	private PermissionModelMapper permMapper;
	@Autowired
	private RolePermModelMapper rolePermMapper;
	@Autowired
	private MemberRoleModelMapper mebRoleMapper;
	
	/**
	 * 根据登录名获取用户
	 * @param loginname
	 * @return
	 */
	public MemberModel getMemberByName(String loginname){
		MemberModelCriteria example = new MemberModelCriteria();
		example.createCriteria().andLoginNameEqualTo(loginname);
		List<MemberModel> memberList = memberMapper.selectByExample(example);
		if(memberList != null && memberList.size() > 0){
			return memberList.get(0);
		}
		
		return null;
	}
	
	public List<RoleModel> selectRoleByMemberId(Integer memberId){
		List<Integer> roleIds = selectRoleIdByMemberId(memberId);
		if(roleIds.size() > 0){
			RoleModelCriteria roleExample = new RoleModelCriteria();
			roleExample.createCriteria().andIdIn(roleIds);
			List<RoleModel> roleList = roleMapper.selectByExample(roleExample);
			return roleList;
		}
		
		return null;
	}
	
	public List<PermissionModel> selectPermissionByMemberId(Integer memberId){
		List<Integer> roleIds = selectRoleIdByMemberId(memberId);
		List<Integer> permIds = selectPermIdByMemberId(roleIds);
		if(permIds.size() > 0){
			PermissionModelCriteria example = new PermissionModelCriteria();
			example.createCriteria().andIdIn(permIds);
			List<PermissionModel> permList = permMapper.selectByExample(example);
			return permList;
		}
		
		return null;
	}
	
	public List<Integer> selectRoleIdByMemberId(Integer memberId){
		MemberRoleModelCriteria example = new MemberRoleModelCriteria();
		example.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberRoleModel> list = mebRoleMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			List<Integer> roleIds = new ArrayList<Integer>();
			for(MemberRoleModel model : list){
				roleIds.add(model.getRoleId());
			}
			return roleIds;
		}
		
		return null;
	}
	
	public List<Integer> selectPermIdByMemberId(List<Integer> roleIds){
		RolePermModelCriteria example = new RolePermModelCriteria();
		example.createCriteria().andRoleIdIn(roleIds);
		List<RolePermModel> list = rolePermMapper.selectByExample(example);
		if(list != null && list.size() > 0){
			List<Integer> permIds = new ArrayList<Integer>();
			for(RolePermModel model : list){
				permIds.add(model.getPermId());
			}
			return permIds;
		}
		
		return null;
	}
}
