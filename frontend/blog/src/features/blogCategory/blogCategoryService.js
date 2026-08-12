import {axiosClient} from "axios";

//CREATE
export function createBlogCategories(payload) {
    return axiosClient.post(ENDPOINT.BLOG_CATEGORY.CREATE, payload);
}


//LIST
export function listBlogCategories(params) {
    return axiosClient.get(ENDPOINT.BLOG_CATEGORY.LIST, params);
}


//FIND
export function findByIdBlogCategories(id) {
    return axiosClient.get(ENDPOINT.BLOG_CATEGORY.FIND(id));
}


//UPDATE
export function updateBlogCategories(id, payload) {
    return axiosClient.put(ENDPOINT.BLOG_CATEGORY.UPDATE(id), payload);
}


//DELETE
export function deleteBlogCategories(id) {
    return axiosClient.delete(ENDPOINT.BLOG_CATEGORY.DELETE(id));
}