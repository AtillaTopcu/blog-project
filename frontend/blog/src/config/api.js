export const API_BASE =
    import.meta?.env?.VITE_API_BASE ||
    process.env.REACT_APP_API_BASE ||
    'http://localhost:5555/';

export const IMAGE_BASE =
    import.meta?.env?.VITE_IMAGE_BASE ||
    process.env.REACT_APP_IMAGE_BASE ||
    API_BASE;

export const ENDPOINT = {
    //LOGIN
    LOGIN: '/auth/api/v1.0.0/login',
    //REGISTER
    REGISTER_CREATE: (rolesId = 1) => `/register/api/v1.0.0/create${rolesId}`,
    //ME
    ME: '/auth/api/v1.0.0/me',
    //ROLEs
    ROLES: {
        LIST: '/roles/api/v1.0.0',
    },

    //BLOG CATEGORY
    BLOG_CATEGORY :{
        LIST: '/blog/category/api/v1.0.0/list',
        CREATE: '/blog/category/api/v1.0.0/create',
        FIND: (id) => `/blog/category/api/v1.0.0/find/${id}`,
        UPDATE: (id) => `/blog/category/api/v1.0.0/update/${id}`,
        DELETE: (id) => `/blog/category/api/v1.0.0/delete/${id}`,
    },

    //BLOG
    BLOG :{
        LIST: '/blog/api/v1.0.0/list',
        CREATE: '/blog/api/v1.0.0/create',
        FIND: (id) => `/blog/api/v1.0.0/find/${id}`,
        UPDATE: (id) => `/blog/api/v1.0.0/update/${id}`,
        DELETE: (id) => `/blog/api/v1.0.0/delete/${id}`,
    },
}