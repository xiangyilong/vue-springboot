import Vue from 'vue'
import Router from 'vue-router'

// import AppIndex from '@/components/home/AppIndex'
import Login from '@/components/Login'
import Home from '@/components/Home'
// import LibraryIndex from '@/components/library/LibraryIndex'
import requestSource from '@/userpages/requestSource'
import requestProgress from '@/userpages/requestProgress'
import requestRecord from '@/userpages/requestRecord'
import approving from '@/adminpages/approving'
import approved from '@/adminpages/approved'
import sourceManagement from '@/adminpages/sourceManagement'
import register from '@/components/register'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      // home页面不需要访问
      // redirect: '/index',
      children: [
        // {
        //   path: '/index',
        //   name: 'AppIndex',
        //   component: AppIndex,
        //   meta: {
        //     requireAuth: true
        //   }
        // },
        // {
        //   path: '/library',
        //   name: 'Library',
        //   component: LibraryIndex,
        //   meta: {
        //     requireAuth: true
        //   }
        // },
        {
          path: '/requestSource',
          name: 'requestSource',
          component: requestSource,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/requestProgress',
          name: 'requestProgress',
          component: requestProgress,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/requestRecord',
          name: 'requestRecord',
          component: requestRecord,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/approving',
          name: 'approving',
          component: approving,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/approved',
          name: 'approved',
          component: approved,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/sourceManagement',
          name: 'sourceManagement',
          component: sourceManagement,
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/',
      // name: 'login',
      redirect: '/login',
      // component: Login,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'register',
      component: register,
      meta: {
        requireAuth: true
      }
    }
  ]
})
