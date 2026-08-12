import React from 'react'
import React, { useEffect, useState, useMemo } from 'react'

import { API_BASE, ENDPOINTS } from '../../config/api';
import { showSuccess, showError } from './resuability/toastHelper';
import axios from 'axios'; // react + spring boot backend için axios kullanıyoruz. fetch api de kullanılabilir ama axios daha kolay ve yaygın bir kullanım sunuyor.

// Helpers
const extractData = (response) => {
    const temp = response?.data;
    return temp?.data ?? temp?.result ?? temp?.items ?? temp?.content ?? temp ?? [];
};

// Format Date (+3)
const fmtDate = (iso) =>
    !iso ? '' : new Date(iso).toLocaleDateString('tr-TR', { timezone: 'Europe/Istanbul' });

function GlobalBackdrop({ show, onClose }){
    if (!show) return null;
    return (
        <div className="modal-backdrop fade show" style={{ zIndex: 1040 }} onClick={onClose || undefined}/>
    );

    function BlogCategory() {

        // State
        const [items, setItems] = useState([]);
        const [loading, setLoading] = useState(false);  
    
        // Modals 
        const [showCreate, setShowCreate] = useState(false);
        const [showEdit, setShowEdit] = useState(false);
        const [showView, setShowView] = useState(false);
        const [showDelete, setShowDelete] = useState(false);

        // Selection + Form
        const [selected, setSelected] = useState(null);
        const [form, setForm] = useState({ categoryName: '' });
        const [formError, setFormError] = useState({});

        // Filter + Sort + Pagination
        const [query, setQuery] = useState('');
        const [sortKey, setSortKey] = useState('categoryId'); // categoryId | categoryName | createdAt
        const [sortDir, setSortDir] = useState('asc'); // asc | desc
        const [page, setPage] = useState(1); // mevcut sayfa numarası
        const [pageSize, setPageSize] = useState(10); // veri sayfası başına gösterilecek öğe sayısı

        const anyOpen = showCreate || showEdit || showView || showDelete;

        // useEffect
        useEffect(()=>{
            if (anyOpen) {
                document.body.classList.add('modal-open');
            }else {
                document.body.classList.remove('modal-open');
            }
        }, [anyOpen]);

        useEffect(() => {
            const onKey = (e) => {
                if (e.key != 'Escape') return;
                if (showCreate) return closeCreate();
                if (showEdit) return closeEdit();
                if (showView) return closeView();
                if (showDelete) return closeDelete();
            };
            window.addEventListener('keydown', onKey);
            return () => {
                window.removeEventListener('keydown', onKey);
            };
        }, [showCreate, showEdit, showView, showDelete]);

        const fectList = () => {
            setLoading(true);
            try {
                const res = await axios.get($`{API_BASE}${ENDPOINTS.BLOG_CATEGORY_LIST}`);
                const data = extractData(res);
                const arr = Array.isArray(data) ? data : Array.isArray(data?.content) ? data.content : [];
                setItems(arr);
            } catch (error) {
                showError?.('Blog kategori listesi alınamadı.') ?? console.error(error);
            } finally {
                setLoading(false);
            }
        }

        // useEffect (Api)
        useEffect(() => {
            fectList();
        }, []);

        const closeAll = () => {
            setShowCreate(false);
            setShowEdit(false);
            setShowView(false);
            setShowDelete(false);
        };

        const resetForm = () => {
            setForm({ categoryName: '' });
            setFormError({});
        }

        const openCreate = () => {
            closeAll();
            resetForm();
            setShowCreate(true);
        }

        const closeCreate = () => {
            setShowCreate(false);
            resetForm();
        }

        const openEdit = (row) => {
            closeAll();
            setSelected(row);
            setForm({ categoryName: row.categoryName ?? '' });
            setFormError({});
            setShowEdit(true);
        }

        const closeEdit = () => {
            setShowEdit(false);
            setSelected(null);
            resetForm();
        }

        const openView = (row) => {
            closeAll();
            setSelected(row);
            setShowView(true);
        }

        const closeView = () => {
            setShowView(false);
            setSelected(null);
        }

        const openDelete = (row) => {
            closeAll();
            setSelected(row);
            setShowDelete(true);
        }

        const closeDelete = () => {
            setShowDelete(false);
            setSelected(null);
        }

        // Pagination
        const filtered = useMemo(() => {
            const q = query.trim().toLowerCase();
            if (!q) return items;
            return items.filter((x) => {
                const id = (x.categoryId ?? x.id ?? '').toString();
                const name = (x.categoryName ?? '').toString();
                return id.includes(q) || name.includes(q);
            });
        }, [items, query]);

        const sorted = useMemo(() => {
            const arr = [...filtered];
            arr.sort((a, b) => {
                const va = 
                    sortkey == 'categoryName' ? 
                    (a.categoryName ?? '').toLowerCase() 
                    : sortkey == 'systemCreatedAt'
                    ? new Date(a.systemCreatedAt ?? 0).getTime()
                    : a.categoryId ?? a.id ?? 0;
                const vb =
                    sortkey == 'categoryName' ? 
                    (b.categoryName ?? '').toLowerCase()
                    : sortkey == 'systemCreatedAt'
                    ? new Date(b.systemCreatedAt ?? 0).getTime()
                    : b.categoryId ?? b.id ?? 0;
                const r = (va < vb) ? -1 : va > vb ? 1 : 0;
                return sortDir == 'asc' ? r : -r;   
            });
            return arr;
        }, [filtered, sortKey, sortDir]);
        
        const total = sorted.length;
        const pageCount = Math.max(1, Math.ceil(total / pageSize));
        const currentPage = Math.min(page, pageCount);
        const paged = useMemo(() => {
            const start = (currentPage - 1) * pageSize;
            return sorted.slice(start, start + pageSize);
        }, [sorted, currentPage, pageSize]);

        // Form Helpers
        const resetForm = () => {
            setForm({ categoryName: '' });
            setFormError({});
        };

        const onChange = (event) => {
            const { name, value } = event.target;
            setForm((temp) => ({...temp, [name]:value}));
            setFormError((temp) => ({...temp, [name]:undefined}));
        };

        const submitCreate = async (event) => {
            event.preventDefault();
            const err = {};
            if (!form.categoryName?.trim())
                err.categoryName = 'Kategori adı boş olamaz.';
            setFormError(err);

            if (Object.keys(err).length > 0) return;

            try {
                const res = await axios.post(`${API_BASE}${ENDPOINTS.BLOG_CATEGORY_CREATE}`, {
                    categoryName: form.categoryName?.trim(),
                });
                const data = extractData(res);
                showSuccess?.('Kategori başarıyla oluşturuldu.') ?? console.log('Kategori başarıyla oluşturuldu.', res.data);
                closeCreate();
                fectList();
            } catch (error) {
                showError?.(error?.response?.data?.message || 'Kategori oluşturulamadı.') ?? console.error(error);
                setFormError(error?.response?.data?.validationErrors || {});
            };
        };

        const submitUpdate = async (event) => {
            event.preventDefault();
            const err = {};
            if (!form.categoryName?.trim())
                err.categoryName = 'Kategori adı boş olamaz.';
            setFormError(err);

            if (Object.keys(err).length > 0) return;

            try {
                const id = selected?.categoryId ?? selected?.id;
                if (id == null) {
                    throw new Error('Blog kategori id bulunamadı.');
                }
                const res = await axios.put(`${API_BASE}${ENDPOINTS.BLOG_CATEGORY_UPDATE}/${id}`, {
                    categoryName: form.categoryName?.trim(),
                });
                const data = extractData(res);
                showSuccess?.('Kategori başarıyla güncellendi.') ?? console.log('Kategori başarıyla güncellendi.', res.data);
                closeEdit();
                fectList();
            } catch (error) {
                showError?.(error?.response?.data?.message || 'Kategori güncellenemedi.') ?? console.error(error);
                setFormError(error?.response?.data?.validationErrors || {});
            };
        };

        const confirmDelete = async (event) => {
            try {
                const id = selected?.categoryId ?? selected?.id;
                if (id == null) {
                    showError?.('Blog kategori id bulunamadı.') ?? console.error('Blog kategori id bulunamadı.');
                    return;
                }
                const res = await axios.delete(`${API_BASE}${ENDPOINTS.BLOG_CATEGORY_DELETE}/${id}`);
                const data = extractData(res);
                showSuccess?.('Kategori başarıyla silindi.') ?? console.log('Kategori silindi.', res.data);
                closeDelete();
                fectList();
            } catch (error) {
                showError?.(error?.response?.data?.message || 'Kategori silinemedi.') ?? console.error(error);
            }
        };

        const SortBtn = ({ k, children }) => (
            <button
                type="button"
                className="btn btn-link p-0 ms-1"
                title="Sırala"
                onClick={() => {
                    if (sortKey == k) {
                        setSortDir((temp) => (temp == 'asc' ? 'desc' : 'asc'));
                    } else {
                        setSortKey(k);
                        setSortDir('asc');
                    }
                }}
            >
                {children} {sortKey == k ? (sortDir == 'asc' ? '▲' : '▼') : ''}
            </button>
        );

        return (
        <React.Fragment>
            <div className="container py-4">
                <div class="d-flex align-items-center justify-content-between">
                    <h2 className="mb-0">Blog Kategoriler</h2>
                    <div className="d-flex gap-2">
                        <input type="text"
                        className="form-control"
                        placeholder="Kategori ara (id/ad) ..."
                        value={query}
                        onChange={(e) => {
                            setQuery(e.target.value);
                            setPage(1);
                        }}
                        />
                        <button type="button" className="btn btn-primary" onClick={openCreate}>
                            Yeni Kategori
                        </button>
                    </div>
                </div>

                <div className="table-responsive">
                    <table className="table table-striped table-bordered align-middle">
                        <thead>
                            <tr>
                                <th style={{ minWidth: 90 }}> ID <SortBtn k="categoryId"/></th>
                                <th style={{ minWidth: 120 }}> Kategori Adı <SortBtn k="categoryName"/></th>
                                <th style={{ minWidth: 220 }}> Oluşturulma Tarihi <SortBtn k="systemCreatedAt"/></th>
                                <th style={{ minWidth: 220 }}> İşlemler</th>
                                <th style={{ width: '120px' }} className="text-center"> İşlemler </th>
                            </tr>
                        </thead>
                        <tbody>
                            {loading ? (
                                <td colSpan={5} className="text-center py-1">
                                    <div className="spinner-border spinner-border-sm me-2 text-primary" role="status"></div>
                                    <span className="visually-hidden">Yükleniyor...</span>
                                </td>
                            ) : paged.length == 0 ? (
                                <td colSpan={4} className="text-center py-1">
                                    <span className="text-muted">Kayıt Bulunamadı.</span>
                                </td>
                            ) : }
                        </tbody>
                    </table>
                </div>
            </div>
        </React.Fragment>
        );
    } // end BlogCategory
};

export default BlogCategory